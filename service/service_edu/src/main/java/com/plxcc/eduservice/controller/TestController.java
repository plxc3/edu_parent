package com.plxcc.eduservice.controller;

import com.plxcc.servicebase.common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @PackgeName: com.plxcc.eduservice.controller
 * @ClassName: TestController
 * @Author: plxc
 * Date: 2020/8/16 11:01
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@Controller
@RestController
public class TestController {

    @ApiOperation(tags = {"演示"},value = "test")
    @GetMapping("/test/test")
    public Result test(){
        return Result.success().setMsg("者是一个测试接口");
    }
    @PostMapping("/psot/{id}")
    @ApiOperation(tags = {"演示"},value = "test1")
    public Result postId(@PathVariable String id){
        return Result.success().setMsg(id);
    }

}
