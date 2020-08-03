package com.plxcc.eduservice.client;

import com.plxcc.eduservice.client.impl.VodClientImpl;
import com.plxcc.servicebase.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @PackgeName: com.plxcc.eduservice.client
 * @ClassName: VodClient
 * @Author: plxc
 * Date: 2020/7/30 22:45
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@Component
@FeignClient(name = "service-vod",fallback = VodClientImpl.class)
public interface VodClient {

    //定义要调用的方法路径
    @DeleteMapping("/vod/video/removeVideo/{id}")
    public Result removeVideo(@PathVariable("id") String id);

}
