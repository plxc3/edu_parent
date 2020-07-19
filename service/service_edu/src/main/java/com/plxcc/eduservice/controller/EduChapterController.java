package com.plxcc.eduservice.controller;


import com.plxcc.eduservice.entity.EduChapter;
import com.plxcc.eduservice.entity.vo.course.chapter.ChapterVo;
import com.plxcc.eduservice.service.EduChapterService;
import com.plxcc.servicebase.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/service/edu-chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    /**
     * 课程大纲列表,
     * 要根据课程id进行查询
     */
    @GetMapping("/getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable String courseId){

        List<ChapterVo>  chapterVoList=eduChapterService.getChapterVideoByCourseId(courseId);
        return Result.success()
                .setData("courseChapterVideo",chapterVoList);
    }


}

