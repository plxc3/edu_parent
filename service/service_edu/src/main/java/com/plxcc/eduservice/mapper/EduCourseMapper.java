package com.plxcc.eduservice.mapper;

import com.plxcc.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plxcc.eduservice.entity.vo.course.CoursePublishInfoVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author plxc3
 * @since 2020-07-12
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishInfoVo getPublishInfo(String courseId);

}
