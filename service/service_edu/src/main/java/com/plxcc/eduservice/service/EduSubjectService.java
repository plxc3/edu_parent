package com.plxcc.eduservice.service;

import com.plxcc.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.plxcc.eduservice.entity.vo.subject.SubjectOne;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author plxc3
 * @since 2020-07-09
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<SubjectOne> getAllSubject();
}
