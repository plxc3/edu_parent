package com.plxcc.eduservice.entity.vo.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackgeName: com.plxcc.eduservice.entity.vo.subject
 * @ClassName: SubjectOne
 * @Author: plxc
 * Date: 2020/7/11 12:24
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@Data
public class SubjectOne {

    private String id;
    private String title;
    /**
     * 一个一级分类中有多个二级分类
     */
    private List<SubjectTwo> children=new ArrayList<SubjectTwo>();

}
