import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.plxcc.vod.utils.ConstantVodUtils;
import org.junit.Test;

/**
 * @PackgeName: com.plxcc.test
 * @ClassName: Init
 * @Author: plxc
 * Date: 2020/7/27 1:06
 * project name: edu_parent
 * @Version:
 * @Description:
 */
public class Init {

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        /**
         * 接入区域不能更改
         */
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

}
