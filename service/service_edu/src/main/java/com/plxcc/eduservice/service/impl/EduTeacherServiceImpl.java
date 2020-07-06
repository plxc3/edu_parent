package com.plxcc.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plxcc.eduservice.entity.EduTeacher;
import com.plxcc.eduservice.entity.vo.TeacherQuery;
import com.plxcc.eduservice.mapper.EduTeacherMapper;
import com.plxcc.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plxcc.servicebase.common.Result;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author plxc3
 * @since 2020-07-04
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Override
    public Result pageTeacherConditon(TeacherQuery teacherQuery) {
        Page<EduTeacher> pageTeacher=new Page<>(teacherQuery.getCurrent(),teacherQuery.getSize());
        QueryWrapper<EduTeacher> queryWrapper=new QueryWrapper<>();
        //动态sql
        String name=teacherQuery.getName();
        Integer level=teacherQuery.getLevel();
        String begin=teacherQuery.getBegin();
        String end=teacherQuery.getEnd();
        if(StringUtils.checkValNotNull(name)){
            queryWrapper.like("name",name);
        }
        if(StringUtils.checkValNotNull(level)){
            queryWrapper.eq("level",level);
        }
        if(StringUtils.checkValNotNull(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if(StringUtils.checkValNotNull(end)){
            queryWrapper.le("gmt_modified",end);
        }
        page(pageTeacher,queryWrapper);
        Map map=new HashMap();
        map.put("total",pageTeacher.getTotal());
        map.put("records",pageTeacher.getRecords());
        return Result
                .success()
                .setData(map)
                .setMsg("条件分页查询");
    }
}
