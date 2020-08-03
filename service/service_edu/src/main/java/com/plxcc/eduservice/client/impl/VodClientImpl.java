package com.plxcc.eduservice.client.impl;

import com.plxcc.eduservice.client.VodClient;
import com.plxcc.servicebase.common.Result;
import org.springframework.stereotype.Component;

/**
 * @PackgeName: com.plxcc.eduservice.client.impl
 * @ClassName: VodClientImpl
 * @Author: plxc
 * Date: 2020/7/31 1:10
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@Component
public class VodClientImpl implements VodClient {

    /**
     * 出错之后会执行
     * @param id
     * @return
     */
    @Override
    public Result removeVideo(String id) {
        return Result.fail().setMsg("删除视频出错");
    }

}
