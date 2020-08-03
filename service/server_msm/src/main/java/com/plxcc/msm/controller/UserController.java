package com.plxcc.msm.controller;


import com.plxcc.msm.entity.User;
import com.plxcc.msm.entity.vo.RegisterVo;
import com.plxcc.msm.service.UserService;
import com.plxcc.servicebase.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/login")
    public Result Login(@RequestBody User user){
        //登陆并返回token值
       String token= userService.login(user);

       return Result.success().setData("token",token);
    }

    /**
     * Register
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo){
        userService.register(registerVo);
        return Result.success();
    }

}

