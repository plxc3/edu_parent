package com.plxcc.eduservice.controller;

import com.plxcc.servicebase.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @PackgeName: com.plxcc.eduservice.controller
 * @ClassName: EduLoginController
 * @Author: plxc
 * Date: 2020/7/6 21:14
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@Controller
@ResponseBody
@RequestMapping("/service/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("/login")
    public Result login(){
        return Result.success().setData("token","admin");
    }

    @GetMapping("/info")
    public  Result info(){
        Map map=new HashMap();
        map.put("roles","admin");
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.success()
                .setData(map);
    }

}
