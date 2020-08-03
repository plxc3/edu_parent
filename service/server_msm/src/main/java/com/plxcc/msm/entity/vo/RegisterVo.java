package com.plxcc.msm.entity.vo;

import lombok.Data;

/**
 * @PackgeName: com.plxcc.msm.entity.vo
 * @ClassName: RegisterVo
 * @Author: plxc
 * Date: 2020/8/3 14:54
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@Data
public class RegisterVo {

    private String email;

    private String phone;

    private String password;

    //验证码
    private String code;

    private String userName;

}
