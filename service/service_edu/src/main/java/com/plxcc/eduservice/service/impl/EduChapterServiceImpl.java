package com.plxcc.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.plxcc.eduservice.entity.EduChapter;
import com.plxcc.eduservice.entity.EduVideo;
import com.plxcc.eduservice.entity.vo.course.chapter.ChapterVo;
import com.plxcc.eduservice.entity.vo.course.chapter.VideoVo;
import com.plxcc.eduservice.mapper.EduChapterMapper;
import com.plxcc.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plxcc.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author plxc3
 * @since 2020-07-12
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        /**
         *1. 根据可课程id查询出所有章节；
         */
        QueryWrapper<EduChapter> wrapperChapter=new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapters= baseMapper.selectList(wrapperChapter);

        /**
         * 2.根据课程id查询出所有的小节
         */
        QueryWrapper<EduVideo> wrapperVideo=new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoService.list(wrapperVideo);
        /**
         * 创建list集合用于最终的数据封装
         */
        List<ChapterVo> finalList=new ArrayList<>();

        /**
         * 3.遍历查询所有章节list集合进行封装
         */
        for(int i=0;i<eduChapters.size();i++){
            ChapterVo chapterVo=new ChapterVo();
            BeanUtils.copyProperties(eduChapters.get(i),chapterVo);
            /**
             * 遍历查询所有小节list集合进行封装
             */
            List<VideoVo> children=new ArrayList<>();
            for(int m=0;m<eduVideos.size();m++){
                if(chapterVo.getId().equals(eduVideos.get(m).getChapterId())){
                    VideoVo videoVo=new VideoVo();
                    BeanUtils.copyProperties(eduVideos.get(m),videoVo);
                    children.add(videoVo);
                }
            }
            chapterVo.setChildren(children);
            finalList.add(chapterVo);
        }




        return finalList;
    }
}
