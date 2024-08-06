package com.imooc.pan.server.modules.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.pan.server.modules.user.context.UserLoginContext;
import com.imooc.pan.server.modules.user.context.UserRegisterContext;
import com.imooc.pan.server.modules.user.entity.RPanUser;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
public interface IUserService extends IService<RPanUser> {

    Long register(UserRegisterContext userRegisterContext);

    String login(UserLoginContext userLoginContext);

    void exit(Long aLong);
}
