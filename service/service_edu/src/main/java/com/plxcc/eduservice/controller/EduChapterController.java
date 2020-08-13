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

    /**Course
     * 课程大纲列表,
     * 要根据课程id进行查询
     */
    @GetMapping("/getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable String courseId){

        List<ChapterVo>  chapterVoList=eduChapterService.getChapterVideoByCourseId(courseId);
        return Result.success()
                .setData("courseChapterVideo",chapterVoList);
    }
/**
 * 添加章节
 */
    @PostMapping("/addChapter")
    public Result addChapter(@RequestBody EduChapter chapter){
        eduChapterService.save(chapter);
        return  Result.success();
    }

    /**
     * 根据id查询章节
     * @param chapterId
     * @return
     */
    @GetMapping("/getChapterById/{chapterId}")
    public Result getChpterById(@PathVariable String chapterId){
        eduChapterService.getById(chapterId);
        return Result.success();
    }

    /**
     * 修改章节
     */
    @PostMapping("/updateChapter")
    public Result updateChapter(@RequestBody EduChapter chapter){
        eduChapterService.updateById(chapter);
        return Result.success();
    }
    /**
     * 删除
     */
    @DeleteMapping("/deleteChapter/{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId){
        boolean flag=eduChapterService.deleteChpapter(chapterId);
        return Result.success();
    }




}

