package com.plxcc.eduservice.entity.vo.course;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @PackgeName: com.plxcc.eduservice.entity.vo.course
 * @ClassName: CousePublishVo
 * @Author: plxc
 * Date: 2020/7/22 23:25
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@Data
public class CoursePublishInfoVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectOne;
    private String subjectTwo;
    private String teacherName;
    private String price;//只用于显示
    private String status;
}
