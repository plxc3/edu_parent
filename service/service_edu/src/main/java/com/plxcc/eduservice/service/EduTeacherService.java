package com.plxcc.eduservice.service;

import com.plxcc.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.plxcc.eduservice.entity.vo.TeacherQuery;
import com.plxcc.servicebase.common.Result;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author plxc3
 * @since 2020-07-04
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Result pageTeacherConditon(TeacherQuery teacherQuery);
}
