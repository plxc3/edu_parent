package com.plxcc.eduservice.controller;


import com.plxcc.eduservice.entity.EduCourse;
import com.plxcc.eduservice.entity.vo.course.CourseInfo;
import com.plxcc.eduservice.service.EduCourseService;
import com.plxcc.servicebase.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author plxc3
 * @since 2020-07-12
 */
@RestController
@CrossOrigin
@RequestMapping("/service/edu-course")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("/addCourseInfo")
    public Result addCorseInfo(@RequestBody CourseInfo courseInfo){

        String id=eduCourseService.saveCourseInfo(courseInfo);
        System.out.println(id);
        return Result.success()
                .setData("courseId",id)
                .setMsg("返回存储对象的ID");
    }
    /**
     * 根据课程id查询课程信息，用CourseInfoVo进行封装，因课程信息有两张表组成
     */
    @GetMapping("/getCourseInfo/{courseId}")
    public Result getCOurseInfo(@PathVariable String courseId){
        CourseInfo courseInfo=eduCourseService.getCourseInfo(courseId);
        return Result.success()
                .setMsg("课程信息查询ByID，用于数据回显")
                .setData("courseInfo",courseInfo);
    }


}

