package com.plxcc.msm.service.impl;

import com.plxcc.msm.entity.UserProfile;
import com.plxcc.msm.mapper.UserProfileMapper;
import com.plxcc.msm.service.UserProfileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author plxc3
 * @since 2020-08-05
 */
@Service
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfile> implements UserProfileService {

}
