package com.plxcc.msm.service;

import java.util.Map;

/**
 * @PackgeName: com.plxcc.msm.controller.service
 * @ClassName: MsmService
 * @Author: plxc
 * Date: 2020/8/2 18:23
 * project name: edu_parent
 * @Version:
 * @Description:
 */
public interface MsmService {
    Boolean sendCode(Map<String, Object> param, String phone);
}
