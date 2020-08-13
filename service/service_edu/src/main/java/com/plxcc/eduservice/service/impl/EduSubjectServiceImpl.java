package com.plxcc.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.plxcc.eduservice.entity.EduSubject;
import com.plxcc.eduservice.entity.excel.SubjectData;
import com.plxcc.eduservice.entity.vo.subject.SubjectOne;
import com.plxcc.eduservice.entity.vo.subject.SubjectTwo;
import com.plxcc.eduservice.mapper.EduSubjectMapper;
import com.plxcc.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plxcc.eduservice.utils.SubjectExcelListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author plxc3
 * @since 2020-07-09
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public List<SubjectOne> getAllSubject() {
        //查询出一级分类
        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
        wrapper.eq("parent_id","0");
        //查询出二级分类
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper<>();
        queryWrapper.ne("parent_id","0");

        List<EduSubject> oneSubjectList=baseMapper.selectList(wrapper);

        List<SubjectOne> finalSubject=new ArrayList<SubjectOne>();
        for(int i=0;i<oneSubjectList.size();i++){
            SubjectOne subjectOne=new SubjectOne();
            BeanUtils.copyProperties(oneSubjectList.get(i),subjectOne);
            /**
             * 查询二级分类
             */
            List<EduSubject> twoSubjectList=baseMapper.selectList(queryWrapper);
            /**
             * 创建children列表
             */
            List<SubjectTwo> children=new ArrayList<SubjectTwo>();
            for(int m=0;m<twoSubjectList.size();m++){
                /**
                 * 判断是否属于当前一级分类
                 */
                if(twoSubjectList.get(m).getParentId().equals(subjectOne.getId())){
                    SubjectTwo subjectTwo=new SubjectTwo();
                    BeanUtils.copyProperties(twoSubjectList.get(m),subjectTwo);
                    children.add(subjectTwo);
                }
            }
            subjectOne.setChildren(children);
            finalSubject.add(subjectOne);
        }
//        System.out.println(finalSubject);
        return finalSubject;
    }

    /**
     * 读取Excel文件，第一行为表头，默认不读取
     * @param file
     * @param eduSubjectService
     */
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {

        try{

            InputStream in=file.getInputStream();
            //调用方法读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();

        }catch (Exception e){

            e.printStackTrace();
        }

    }
}
