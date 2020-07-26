package com.plxcc.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.plxcc.eduservice.entity.EduVideo;
import com.plxcc.eduservice.mapper.EduVideoMapper;
import com.plxcc.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author plxc3
 * @since 2020-07-12
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Override
    public void deleteByCourseId(String courseId) {
        QueryWrapper<EduVideo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.delete(queryWrapper);
    }
}
