import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;


/**
 * @PackgeName: com.plxcc.test
 * @ClassName: TestVod
 * @Author: plxc
 * Date: 2020/7/27 1:09
 * project name: edu_parent
 * @Version:
 * @Description:
 */
public class TestVod {
    public static void main(String[] args) throws ClientException {
        /**
         * 根据视频id获取视频的播放凭证
         */
        //创建初始化对象
        DefaultAcsClient client=Init.initVodClient("LTAI4GKbm3sbKrQutNLfpD6g","otsw0JVsvBW5hsBbmXn3lMN3ox1FiL");
        //创建获取视频凭证的res和req对象
        GetVideoPlayAuthRequest request=new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response=new GetVideoPlayAuthResponse();
        //向request里设置视频id
        request.setVideoId("3613befd056f4f95b97c802bea98fa4e");
        //调用初始化对象获得凭证
        response=client.getAcsResponse(request);
        System.out.println("playAuth"+response.getPlayAuth());

    }

    public static void getPlayUrl() throws ClientException {
        /**
         * 根据视频id获取播放地址
         */
        //创建初始化对象
        DefaultAcsClient client = Init.initVodClient("LTAI4GKbm3sbKrQutNLfpD6g", "otsw0JVsvBW5hsBbmXn3lMN3ox1FiL");


        //创建获取视频地址的request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        //向request对象里设置视频id
        request.setVideoId("b9c675571e30465da0f88ab3bf220572");


        //调用初始化对象里面的方法传递request，获取数据
        response = client.getAcsResponse(request);


        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");

    }

}
