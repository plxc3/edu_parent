package com.plxcc.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @PackgeName: com.plxcc.eduservice.entity.vo
 * @ClassName: TeacherVo
 * @Author: plxc
 * Date: 2020/7/5 20:05
 * project name: edu_parent
 * @Version:
 * @Description:分页查询数据交互
 */
@Data
public class TeacherQuery {

    private Long current;
    private Long size;

    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间")
    private String begin;

    @ApiModelProperty(value = "查询结束时间")
    private String end;


}
