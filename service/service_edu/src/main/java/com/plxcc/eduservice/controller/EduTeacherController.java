package com.plxcc.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plxcc.eduservice.entity.EduTeacher;
import com.plxcc.eduservice.entity.vo.TeacherQuery;
import com.plxcc.eduservice.service.EduTeacherService;
import com.plxcc.servicebase.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result findAllTeacher(){
        List<EduTeacher> list=eduTeacherService.list(null);
        return Result.success().setData("items",list)
                .setMsg("所有讲师数据查询成功");

    }

    //删除讲师
    @DeleteMapping("/delete/{id}")
    public Result removeTeacher(@PathVariable String id){
          if(eduTeacherService.removeById(id)){
              return Result.success();
          }else {
              return Result.fail();
          }
    }

    //分也查询
    //curert代表当前页，size代表每页的信息记录数
    @PostMapping("/pagetacher/{current}/{limit}")
    public Result pageListTeacher(@PathVariable long current,
                                  @PathVariable long limit){
        Page<EduTeacher> pageTeacher=new Page<>(current,limit);
        //调用方法时，底层封装会把分页的所有数据封装到pageTeacher对象里面
        eduTeacherService.page(pageTeacher,null);

        Map map=new HashMap();
        map.put("total",pageTeacher.getTotal());
        map.put("rows",pageTeacher.getRecords());
        return Result.success()
                .setData(map)
                .setMsg("分页查询数据");

    }
//    //条件分页查询
//    @GetMapping("/getteachercondition/{current}/{limit}")
//    public Result pageTeacherCondition(@PathVariable long current,
//                                       @PathVariable long limit,
//                                       TeacherQuery teacherQuery){
//        //创建page对象
//        Page<EduTeacher> pageTeacher=new Page<>(current,limit);
//        //构造条件
//        QueryWrapper<EduTeacher> queryWrapper=new QueryWrapper<>();
//        //warpper
//        //动态sql
//        String name=teacherQuery.getName();
//        Integer level=teacherQuery.getLevel();
//        String begin=teacherQuery.getBegin();
//        String end=teacherQuery.getEnd();
//        if(StringUtils.checkValNotNull(name)){
//            queryWrapper.like("name",name);
//        }
//        if(StringUtils.checkValNotNull(level)){
//            queryWrapper.eq("level",level);
//        }
//        if(StringUtils.checkValNotNull(begin)){
//                queryWrapper.ge("gmt_create",begin);
//        }
//        if(StringUtils.checkValNotNull(end)){
//            queryWrapper.le("gmt_modified",end);
//        }
//        //调用方法实现分页查询
//        eduTeacherService.page(pageTeacher,queryWrapper);
//        Map map=new HashMap();
//        map.put("total",pageTeacher.getTotal());
//        map.put("records",pageTeacher.getRecords());
//        return Result.success()
//                .setData(map)
//                .setMsg("分页条件查询");
//
//    }

    @PostMapping("/pageteacherCondition")
    public Result pageTeacherConditon(@RequestBody TeacherQuery teacherQuery){
        return  eduTeacherService.pageTeacherConditon(teacherQuery);
    }

}

