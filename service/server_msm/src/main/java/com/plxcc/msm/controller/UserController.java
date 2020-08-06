package com.plxcc.msm.controller;


import com.netflix.ribbon.proxy.annotation.Http;
import com.plxcc.msm.entity.User;
import com.plxcc.msm.entity.vo.LoginVo;
import com.plxcc.msm.entity.vo.RegisterVo;
import com.plxcc.msm.service.UserService;
import com.plxcc.servicebase.common.Result;
import com.plxcc.servicebase.utils.JwtUtils;
import com.plxcc.servicebase.utils.ZTException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.zip.ZipException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author plxc3
 * @since 2020-08-03
 */
@RestController
@RequestMapping("/msm/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Login
     */
    @ApiOperation(value = "密码必填，手机号邮箱其一" ,tags = {"登陆接口"})
    @PostMapping("/login")
    public Result Login(@RequestBody User user){
        //登陆并返回token值
       String token= userService.login(user);

       return Result.success().setData("token",token);
    }

    /**
     * Register
     */
    @ApiOperation(value = "注册接口")
    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo){
        userService.register(registerVo);
        return Result.success();
    }
    /**
     * 根据token获取用户信息
     */
    @GetMapping("auth/getUserInfo")
    public Result getUserInfoByToken(HttpServletRequest request){
        //调用JWT工具类的方法，根据request对象获取头信息，返回用户id
        try {
            String id = JwtUtils.getMemberIdByJwtToken(request);
             LoginVo loginVo=userService.getUserInfo(id);
             return Result.success().setData("loginInfo",loginVo);
        }catch (Exception e){
            e.printStackTrace();
            throw new ZTException(20001,"error");
        }
    }

    /**
     * test
     */
    @GetMapping("/test")
    public Result test(){
        return Result.success().setMsg("hello");
    }


}

