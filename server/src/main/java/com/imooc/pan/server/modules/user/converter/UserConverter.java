package com.imooc.pan.server.modules.user.converter;

import com.imooc.pan.server.modules.user.context.UserLoginContext;
import com.imooc.pan.server.modules.user.context.UserRegisterContext;
import com.imooc.pan.server.modules.user.entity.RPanUser;
import com.imooc.pan.server.modules.user.po.UserLoginPO;
import com.imooc.pan.server.modules.user.po.UserRegisterPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author AprilGouzi
 * version 1.0
 * 用户模块实体转化工具类
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    /**
     * UserRegisterPO转化成UserRegisterContext
     *
     * @param userRegisterPO
     * @return
     */
    UserRegisterContext userRegisterPO2UserRegisterContext(UserRegisterPO userRegisterPO);

    /**
     * UserRegisterContext转RPanUser
     * @param userRegisterContext
     * @return
     */
    @Mapping(target = "password",ignore = true)
    RPanUser userRegisterContext2RPanUser(UserRegisterContext userRegisterContext);

    UserLoginContext userLoginPO2UserLoginContext(UserLoginPO userLoginPO);
}
