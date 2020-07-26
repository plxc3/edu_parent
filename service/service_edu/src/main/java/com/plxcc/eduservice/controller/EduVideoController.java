package com.plxcc.eduservice.controller;


import com.plxcc.eduservice.entity.EduVideo;
import com.plxcc.eduservice.service.EduVideoService;
import com.plxcc.servicebase.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author plxc3
 * @since 2020-07-12
 */
@RestController
@CrossOrigin
@RequestMapping("/service/video")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    /**
     * 增加小节
     */
    @PostMapping("/addVideo")
    public Result addVide(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return Result.success();
    }

    /**
     * 删除小节
     * TODO
     * @param
     * @return
     */
    @DeleteMapping("deleteVideo/{videoId}")
    public Result deleteVideo(@PathVariable String videoId){
        videoService.removeById(videoId);
        return Result.success();
    }

}

