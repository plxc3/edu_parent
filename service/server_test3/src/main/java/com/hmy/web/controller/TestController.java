package com.hmy.web.controller;

import com.plxcc.servicebase.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{
    @RequestMapping("/hello")
    public Result hello()
    {
        return  Result.success().setMsg("hello").setData("msg","hello");
    }
}
