package com.plxcc.eduservice.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.plxcc.eduservice.entity.EduSubject;
import com.plxcc.eduservice.entity.excel.SubjectData;
import com.plxcc.eduservice.service.EduSubjectService;
import com.plxcc.servicebase.common.Result;

import java.util.Map;

/**
 * @PackgeName: com.plxcc.eduservice.utils
 * @ClassName: ExcelListener
 * @Author: plxc
 * Date: 2020/7/9 19:00
 * project name: edu_parent
 * @Version:
 * @Description:
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //SubjectExcelListener不能交给spring管理，需要自己手动new，不能注入其他对象
    //不能实现数据库操作,可通过构造函数传递

    public EduSubjectService subjectService;

    public SubjectExcelListener() {

    }
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //一行一行去读取excle内容
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData==null){
            System.out.println("文件为空");
        }
        //一行一行去读取excle内容,第一个值为一级分类，第二个值为二级分类
       EduSubject oneSubject=this.exitsOneSubject(subjectService,subjectData.getOnesubjectName());
        if(oneSubject==null){
            oneSubject=new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOnesubjectName());
            subjectService.save(oneSubject);
        }
        EduSubject towSubject=this.exitsTwoSubject(subjectService,subjectData.getTwoSubjectName(),oneSubject.getId());
        if(towSubject==null){
            towSubject=new EduSubject();
            towSubject.setTitle(subjectData.getTwoSubjectName());
            towSubject.setParentId(oneSubject.getId());
            subjectService.save(towSubject);
        }


    }

    //判断一级分类不能重复添加
    private EduSubject exitsOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id","0");
        EduSubject onSubject=subjectService.getOne(queryWrapper);
        return onSubject;
    }

    //判断二级分类不能重复添加
    private EduSubject exitsTwoSubject(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",pid);
        EduSubject twoSubject=subjectService.getOne(queryWrapper);
        return twoSubject;
    }


    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
