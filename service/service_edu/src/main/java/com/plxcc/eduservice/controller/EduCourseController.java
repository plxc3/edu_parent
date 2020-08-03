package com.plxcc.eduservice.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plxcc.eduservice.client.VodClient;
import com.plxcc.eduservice.entity.EduCourse;
import com.plxcc.eduservice.entity.EduVideo;
import com.plxcc.eduservice.entity.vo.course.CourseInfo;
import com.plxcc.eduservice.entity.vo.course.CoursePage;
import com.plxcc.eduservice.entity.vo.course.CoursePublishInfoVo;
import com.plxcc.eduservice.service.EduCourseService;
import com.plxcc.eduservice.service.EduVideoService;
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

    //注入vodClient
    @Autowired
    private VodClient vodClient;

    @Autowired
    private EduVideoService videoService;

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
    /**
     * 课程信息修改
     */
    @PostMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfo courseInfo){
        eduCourseService.updateCourseInfo(courseInfo);
        return Result.success();
    }

    /**
     * 课程的最终信息确认
     */
    @GetMapping("/publishInfo/{courseId}")
    public Result getPublishInfo(@PathVariable String courseId){
        CoursePublishInfoVo publishInfo= eduCourseService.getPublishInfo(courseId);
        return Result.success()
                .setMsg("课程的最终信息发布")
                .setData("publishInfo",publishInfo);
    }
    /**
     * 课程最终发布
     */
    @PostMapping("/final/{courseId}")
    public Result finalPublish(@PathVariable String courseId){

        EduCourse course=new EduCourse();
        course.setId(courseId);
        course.setStatus("Normal");
        eduCourseService.updateById(course);
        return Result.success();

    }
    /**
     *  分页查询
     */
    @PostMapping("/pageCourseInfo")
    public Result getPagetCourseInfo(@RequestBody CoursePage coursePage){
        Page<EduCourse> page=new Page<>(coursePage.getCurrent(),coursePage.getSize());
        /**
         * //调用方法时，底层封装会把分页的所有数据封装到pagequery对象里面
         */
        eduCourseService.page(page,null);
        coursePage.setTotal(page.getTotal());
        coursePage.setPublishInfoVoList(page.getRecords());
        return Result.success()
                .setMsg("课程列表")
                .setData("coursePage",coursePage);
    }
    /**
     * 列表课程的删除
     */
    @DeleteMapping("/deletePublisCourse/{courseId}")
    public Result deleteListCourse(@PathVariable String courseId){
        //根据小节id获取视频id，调用方法删除
        EduVideo video=videoService.getById(courseId);
        String videoId=video.getVideoSourceId();
        if(StringUtils.checkValNotNull(videoId)){
            System.out.println("没有视频");
        }
        else {
            vodClient.removeVideo(videoId);
            System.out.println("视频移除成功"+videoId);
        }
        eduCourseService.deleteFinalCourse(courseId);
        return Result.success();
    }


}

