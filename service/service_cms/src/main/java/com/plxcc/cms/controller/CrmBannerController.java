package com.plxcc.cms.controller;


import com.plxcc.cms.entity.CrmBanner;
import com.plxcc.cms.service.CrmBannerService;
import com.plxcc.servicebase.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author plxc3
 * @since 2020-08-01
 */
@RestController
@RequestMapping("/cms/banner")
@CrossOrigin
public class CrmBannerController {
    @Autowired
    private CrmBannerService bannerService;



    /**
     * （1）缓存@Cacheable
     * 根据方法对其返回结果进行缓存，下次请求时，如果缓存存在，
     * 则直接读取缓存数据返回；如果缓存不存在，则执行方法，
     * 并把返回的结果存入缓存中。一般用在查询方法上。
     */
    @Cacheable(value = "banner")
    @GetMapping("/bannerList")
    public Result getBannerList(){
        List<CrmBanner> banners=bannerService.list();
        return Result.success()
                .setMsg("轮播图列表")
                .setData("bannerList",banners);
    }
}

