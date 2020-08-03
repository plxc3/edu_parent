package com.plxcc.msm.service;

import com.plxcc.msm.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.plxcc.msm.entity.vo.RegisterVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author plxc3
 * @since 2020-08-03
 */
public interface UserService extends IService<User> {

    String login(User user);

    void register(RegisterVo registerVo);
}
