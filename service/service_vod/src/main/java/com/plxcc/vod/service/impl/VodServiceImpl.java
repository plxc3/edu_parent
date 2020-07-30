package com.plxcc.vod.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.plxcc.servicebase.utils.ZTException;
import com.plxcc.vod.service.VodService;
import com.plxcc.vod.utils.AliyunVodSDKUtils;
import com.plxcc.vod.utils.ConstantVodUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.SAXParser;
import java.io.InputStream;

/**
 * @PackgeName: com.plxcc.vod.service.impl
 * @ClassName: VodServiceImpl
 * @Author: plxc
 * Date: 2020/7/28 19:51
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideoAly(MultipartFile file) {
        /**
         * title上传后显示的名称
         * filename上传文件的原始名称
         * inputStream 上传文件的输入流
         */
        try {
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();

            String videoId = null;
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.KEY_ID,
                    ConstantVodUtils.KEY_SERCERT,
                    title, fileName,
                    inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
            if (response.isSuccess()) {
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                /**
                 * 获取视频id
                 */
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void removeVideo(String id) {
        try{
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    ConstantVodUtils.KEY_ID,
                    ConstantVodUtils.KEY_SERCERT);

            DeleteVideoRequest request = new DeleteVideoRequest();

            request.setVideoIds(id);

            DeleteVideoResponse response = client.getAcsResponse(request);

            System.out.print("RequestId = " + response.getRequestId() + "\n");

        }catch (Exception e){
            throw new ZTException(20001, "视频删除失败");
        }
    }
}
