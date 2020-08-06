package com.plxcc.msm.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @PackgeName: com.plxcc.msm.entity.vo
 * @ClassName: LoginVo
 * @Author: plxc
 * Date: 2020/8/5 8:35
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@Data
public class LoginVo {

    private String id;

    private String userName;


    @ApiModelProperty(value = "头像")
    private String avatar;

}
