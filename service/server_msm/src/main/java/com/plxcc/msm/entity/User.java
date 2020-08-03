package com.plxcc.msm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author plxc3
 * @since 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String email;

    private String phone;

    private String password;

    private String userName;

    private Boolean isDisable;


}
