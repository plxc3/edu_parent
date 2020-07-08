package com.plxcc.oss.controller;

import com.plxcc.oss.service.OssService;
import com.plxcc.servicebase.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @PackgeName: com.plxcc.oss.controller
 * @ClassName: OssController
 * @Author: plxc
 * Date: 2020/7/8 18:11
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@Controller
@ResponseBody
@RequestMapping("/oss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像方法
    @PostMapping("/")
    public Result uploadOssFile(MultipartFile file) throws Exception {
        //获取上传文件 MultipartFile
        //返回上传的oss的路径

        String url=ossService.uploadAvatar(file);

        return Result.success()
                .setData("url",url)
                .setMsg("头像url");
    }

}
