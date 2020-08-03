package com.plxcc.msm.service.impl;

import com.alibaba.nacos.common.util.Md5Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.plxcc.msm.entity.User;
import com.plxcc.msm.entity.vo.RegisterVo;
import com.plxcc.msm.mapper.UserMapper;
import com.plxcc.msm.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plxcc.msm.utlis.MD5;
import com.plxcc.servicebase.utils.JwtUtils;
import com.plxcc.servicebase.utils.ZTException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author plxc3
 * @since 2020-08-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(User user) {

        String phone=user.getPhone();
        String password=user.getPassword();
        //手机号和密码非空判断
        if(!StringUtils.checkValNotNull(password)||!StringUtils.checkValNotNull(phone)){
            throw new ZTException(20001,"LoginException");
        }
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone);
        User user1=baseMapper.selectOne(userQueryWrapper);

        if(!StringUtils.checkValNotNull(user1)){
            throw new ZTException(20001,"LoginException");
        }
        if(!password.equals(user1.getPassword())){
            throw new ZTException(20001,"LoginException");
        }
        //判断用户是否禁用
       if(user1.getIsDisable()){
           throw new ZTException(20001,"LoginException");
       }

        String token=JwtUtils.getJwtToken(user.getId(),user1.getUserName());

        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册码
        String code=registerVo.getCode();

        String phone=registerVo.getPhone();

        String userName=registerVo.getUserName();

        String password=registerVo.getPassword();

        if(!StringUtils.checkValNotNull(code)||!StringUtils.checkValNotNull(phone)
        ||!StringUtils.checkValNotNull(userName)||!StringUtils.checkValNotNull(password))
        {
            throw new ZTException(20001,"注册失败");
        }

        //验证验证码，从redis中获取
        String rediscode=redisTemplate.opsForValue().get(phone);

        if(!code.equals(rediscode)){
            throw new ZTException(20001,"注册失败");
        }

        //判断手机号是否重复
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone);
        int count=baseMapper.selectCount(userQueryWrapper);
        if(count>0){
            throw new ZTException(20001,"注册失败");
        }

        //添加数据到数据库中
        User user=new User();
        BeanUtils.copyProperties(user,registerVo);
        user.setPassword(MD5.encrypt(password));
        baseMapper.insert(user);






    }
}
