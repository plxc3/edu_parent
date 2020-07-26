package com.plxcc.eduservice.service.impl;

import com.plxcc.eduservice.entity.EduCourse;
import com.plxcc.eduservice.entity.EduCourseDescription;
import com.plxcc.eduservice.entity.EduVideo;
import com.plxcc.eduservice.entity.vo.course.CourseInfo;
import com.plxcc.eduservice.entity.vo.course.CoursePublishInfoVo;
import com.plxcc.eduservice.mapper.EduCourseMapper;
import com.plxcc.eduservice.service.EduChapterService;
import com.plxcc.eduservice.service.EduCourseDescriptionService;
import com.plxcc.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plxcc.eduservice.service.EduVideoService;
import com.plxcc.servicebase.utils.ZTException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author plxc3
 * @since 2020-07-12
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;

    /**
     * video
     */
    @Autowired
    private EduVideoService videoService;
    /**
     * chapter
     */
    @Autowired
    private EduChapterService chapterService;



    /**
     * 添加课程基本信息,注入两张表，课程基本信息表，课程简介表
     * @param courseInfo
     */
    @Override
    public String saveCourseInfo(CourseInfo courseInfo) {
        /**
         * 1.向课程基本信息表添加信
         * 2.存的对象转换为注入的类的对象
         */
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int insert=baseMapper.insert(eduCourse);
        if(insert<=0){
            System.out.println("添加课程信息失败");
            throw new ZTException(20001,"添加课程信息失败");
        }
        /**
         * myvbatisplus
         * 自动配置了逐渐生成并且注入到实体类中
         */
        String courseId=eduCourse.getId();

        /**
         * 2.向课程简介表添加信息
         */
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescription.setId(courseId);
        eduCourseDescriptionService.save(eduCourseDescription);
        return courseId;
    }

    @Override
    public CourseInfo getCourseInfo(String courseId) {
        //查询课程的基本信息
        EduCourse eduCourse=baseMapper.selectById(courseId);
        CourseInfo courseInfo =new CourseInfo();
        BeanUtils.copyProperties(eduCourse,courseInfo);
        //查詢课程的简介
        EduCourseDescription description=eduCourseDescriptionService.getById(courseId);
        courseInfo.setDescription(description.getDescription());
        return  courseInfo;
    }

    @Override
    public void updateCourseInfo(CourseInfo courseInfo) {
        //对课程基本信息进行封装
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int insert=baseMapper.updateById(eduCourse);
        if(insert==0){
            throw new ZTException(20001,"课程更新失败");
        }
        //对课程简介进行封装
        EduCourseDescription description=new EduCourseDescription();
        description.setId(courseInfo.getId());
        description.setDescription(courseInfo.getDescription());
        eduCourseDescriptionService.updateById(description);

    }

    @Override
    public CoursePublishInfoVo getPublishInfo(String courseId) {
        CoursePublishInfoVo publishInfo = baseMapper.getPublishInfo(courseId);
        return publishInfo;
    }
    /**
     * 删除所有课程信息
     */
    @Override
    public void deleteFinalCourse(String courseId) {
        videoService.deleteByCourseId(courseId);
        chapterService.deleteByCourseId(courseId);
        eduCourseDescriptionService.removeById(courseId);
        int insert=baseMapper.deleteById(courseId);
        if(insert<0){
            throw new ZTException(20001,"删除失败，请检查");
        }
    }
}
