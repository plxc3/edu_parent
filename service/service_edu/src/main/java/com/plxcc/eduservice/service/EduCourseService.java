package com.plxcc.eduservice.service;

import com.plxcc.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.plxcc.eduservice.entity.vo.course.CourseInfo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author plxc3
 * @since 2020-07-12
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfo courseInfo);

    CourseInfo getCourseInfo(String courseId);
}
