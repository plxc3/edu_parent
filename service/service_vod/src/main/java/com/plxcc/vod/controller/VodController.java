package com.plxcc.vod.controller;

import com.plxcc.servicebase.common.Result;
import com.plxcc.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @PackgeName: com.plxcc.vod.controller
 * @ClassName: VodController
 * @Author: plxc
 * Date: 2020/7/28 19:50
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@RestController
@RequestMapping("/vod/video")
@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;

    /**
     * 上传视频到阿里云
     */
    @PostMapping("uploadVideo")
    public Result uplodVideo(MultipartFile file) {

        //返回上传视频的id值
        String id = vodService.uploadVideoAly(file);
        return Result.success()
                .setMsg("视频上传成功")
                .setData("videoId", id);
    }

    /**
     * 测试
     */
    @GetMapping("/test")
    public Result getTest() {
        return Result.success()
                .setMsg("测试");
    }

    @DeleteMapping("/removeVideo/{id}")
    public Result removeVideo(@PathVariable String id){
        vodService.removeVideo(id);
        return Result
                .success()
                .setMsg("视频删除成功");
    }

}
