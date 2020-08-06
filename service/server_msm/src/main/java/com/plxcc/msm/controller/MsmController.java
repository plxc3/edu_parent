package com.plxcc.msm.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.plxcc.msm.service.MsmService;
import com.plxcc.msm.utlis.RandomUtil;
import com.plxcc.servicebase.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @PackgeName: com.plxcc.msm.controller
 * @ClassName: MsmController
 * @Author: plxc
 * Date: 2020/8/2 18:22
 * project name: edu_parent
 * @Version:
 * @Description: 阿里云短信服务
 */
@RestController
@RequestMapping("/msm/code")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 发送短信验证码的方法
     */
    @GetMapping("/send/{phone}")
    public Result sendMsm(@PathVariable String phone) {
        //1.从redis中获取验证码，如果获取直接返回
        String code = redisTemplate.opsForValue().get("phone");
        if (StringUtils.checkValNotNull(code)) {
            return Result.success().setMsg("验证码已发送");
        }
        //2.如果获取不到，进行阿里云发送

        //生成随机的数值，传递给阿里云进行发送
        code = RandomUtil.getFourBitRandom();

        Map<String, Object> param = new HashMap<>();

        param.put("code", code);

        Boolean issend = msmService.sendCode(param, phone);

        if (issend) {
            //发送成功，把code放到redis中，并设置过期时间
            redisTemplate.opsForValue().set(phone, code, 15, TimeUnit.MINUTES);
            return Result.success().setMsg("短信验证发送成功");
        }

        return Result.fail().setMsg("短信验证码发送失败");
    }

}
