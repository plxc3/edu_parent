package com.plxcc.msm.service.impl;

import com.alibaba.nacos.common.util.Md5Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.plxcc.msm.entity.User;
import com.plxcc.msm.entity.UserProfile;
import com.plxcc.msm.entity.vo.LoginVo;
import com.plxcc.msm.entity.vo.RegisterVo;
import com.plxcc.msm.mapper.UserMapper;
import com.plxcc.msm.service.UserProfileService;
import com.plxcc.msm.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plxcc.msm.utlis.MD5;
import com.plxcc.servicebase.utils.JwtUtils;
import com.plxcc.servicebase.utils.ZTException;
import io.swagger.annotations.Api;
import org.bouncycastle.asn1.BERApplicationSpecific;
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
    private UserProfileService profileService;

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
        //根据手机号查询用户是否存在并获取用户信息
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone);
        User user1=baseMapper.selectOne(userQueryWrapper);

        if(!StringUtils.checkValNotNull(user1)){
            throw new ZTException(20001,"LoginException");
        }

        //对passwor进行加密与获取的密码进行判断
        if(!user1.getPassword().equals(MD5.encrypt(password))){
            throw new ZTException(20001,"LoginException");
        }

        //判断用户是否禁用
       if(user1.getIsDisable()){
           throw new ZTException(20001,"LoginException");
       }
        //易错点
        String token=JwtUtils.getJwtToken(user1.getId(),user1.getUserName());

        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册码
        String code=registerVo.getCode();

        String phone=registerVo.getPhone();

        String userName=registerVo.getUserName();

        String password=registerVo.getPassword();

        //手机号和密码，验证码都不能为空，一个为空就注册异常，后面应该返回Result结果给前端用
        if(!StringUtils.checkValNotNull(code)||!StringUtils.checkValNotNull(phone)
        ||!StringUtils.checkValNotNull(userName)||!StringUtils.checkValNotNull(password))
        {
            throw new ZTException(20001,"4大件有一个为空");
        }

        //验证验证码，从redis中获取
        String rediscode=redisTemplate.opsForValue().get(phone);
//        System.out.println(rediscode);

//        if(!code.equals(rediscode)){
//            throw new ZTException(20001,"注册码错误");
//        }

        //判断手机号是否重复
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone);
        int count=baseMapper.selectCount(userQueryWrapper);
        if(count>0){
            throw new ZTException(20001,"已经有用户存在");
        }

        //添加数据到数据库中
        User user=new User();
        System.out.println(user);

        BeanUtils.copyProperties(registerVo,user);
        user.setPassword(MD5.encrypt(password));
        //若果user存储成功
        if(baseMapper.insert(user)>0){
            //创建user_profile
            UserProfile userProfile=new UserProfile();
            userProfile.setId(user.getId());
            userProfile.setAvatar("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
            profileService.save(userProfile);
        }

    }

    @Override
    public LoginVo getUserInfo(String id) {
        LoginVo loginVo=new LoginVo();
        BeanUtils.copyProperties(baseMapper.selectById(id),loginVo);
        BeanUtils.copyProperties(profileService.getById(id),loginVo);
        return loginVo;
    }
}
