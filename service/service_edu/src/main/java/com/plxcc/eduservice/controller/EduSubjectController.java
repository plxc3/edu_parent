package com.plxcc.eduservice.controller;


import com.plxcc.eduservice.entity.vo.subject.SubjectOne;
import com.plxcc.eduservice.service.EduSubjectService;
import com.plxcc.servicebase.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author plxc3
 * @since 2020-07-09
 */
@RestController
@RequestMapping("/service/edusubject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;
    //添加课程分类
     //获取上传的文件，把文件的内容读取出来
    @PostMapping("/addsubject")
    public Result addSubbject(MultipartFile file){
        //上传过来的excel文件


        eduSubjectService.saveSubject(file,eduSubjectService);

        return Result.success()
                .setMsg("文件上传成功");
    }

    /**
     * 获取课程分类列表（树形结构，针对返回的数据创建实体类）
     * @return
     */
    @GetMapping("/getAllSubject")
    public Result getAllSubject(){
        List<SubjectOne> list=eduSubjectService.getAllSubject();
        return Result.success()
                .setData("subject",list)
                .setMsg("课程分类列表获取");
    }

}

