package com.plxcc.eduservice.controller;


import com.plxcc.eduservice.entity.EduTeacher;
import com.plxcc.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author plxc3
 * @since 2020-07-04
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    EduTeacherService eduTeacherService;

    //查询所有讲师的数据
    @GetMapping("/findall")
    public List<EduTeacher> findAllTeacher(){

        List<EduTeacher> list=eduTeacherService.list(null);
        return list;
    }

    //删除讲师
    @DeleteMapping("/delete/{id}")
    public boolean removeTeacher(@PathVariable String id){
           boolean flag=eduTeacherService.removeById(id);
           return flag;
    }

}

