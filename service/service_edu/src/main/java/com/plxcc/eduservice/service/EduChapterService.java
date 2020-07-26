package com.plxcc.eduservice.service;

import com.plxcc.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.plxcc.eduservice.entity.vo.course.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author plxc3
 * @since 2020-07-12
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);


    boolean deleteChpapter(String chapterId);

    void deleteByCourseId(String courseId);
}
