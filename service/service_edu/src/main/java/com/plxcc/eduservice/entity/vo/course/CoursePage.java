package com.plxcc.eduservice.entity.vo.course;

import com.plxcc.eduservice.entity.EduCourse;
import lombok.Data;

import java.util.List;

/**
 * @PackgeName: com.plxcc.eduservice.entity.vo.course
 * @ClassName: CoursePage
 * @Author: plxc
 * Date: 2020/7/24 22:41
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@Data
public class CoursePage {
    /**
     * 当前页
     */
    private Long current;

    /**
     * 每页记录数
     */
    private Long size;

    /**
     * 总页数
     */
    private Long total;

    /**
     * 记录数
     */
    private List<EduCourse> publishInfoVoList;

}
