package com.plxcc.eduservice.entity.vo.course.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackgeName: com.plxcc.eduservice.entity.vo.course.chapter
 * @ClassName: ChaptervO
 * @Author: plxc
 * Date: 2020/7/16 17:51
 * project name: edu_parent
 * @Version:
 * @Description:章节
 */
@Data
public class ChapterVo {
    private String id;
    private String title;

    /**
     * 表示小节
     */
    private  List<VideoVo> children =new ArrayList<VideoVo>();

}
