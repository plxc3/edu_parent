package com.plxcc.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @PackgeName: com.plxcc.eduservice.entity.excel
 * @ClassName: SubjectData
 * @Author: plxc
 * Date: 2020/7/9 20:06
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String onesubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;

}
