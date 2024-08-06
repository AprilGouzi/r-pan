package com.imooc.pan.server.modules.user.converter;

import com.imooc.pan.server.modules.file.entity.RPanUserFile;
import com.imooc.pan.server.modules.user.context.*;
import com.imooc.pan.server.modules.user.entity.RPanUser;
import com.imooc.pan.server.modules.user.po.*;
import com.imooc.pan.server.modules.user.vo.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author AprilGouzi
 * version 1.0
 * 用户模块实体转化工具类
 * 可以使用Hutool工具包中的BeanUtil.copyProperties()
 */

/**
 * <h3>属性拷贝的四种方式</h3>
 * <p>
 *     hutool中的BeanUtil.copyProperties()<br>
 *     BeanCopier<br>
 *     orika<br>
 *     mapstruct（推荐）MapStruct 是一个自动生成 bean 映射类的代码生成器。MapStruct 还能够在不同的数据类型之间进行转换。
 * </p>
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

    /**
     * CheckUsernamePO转CheckUsernameContext
     *
     * @param checkUsernamePO
     * @return
     */
    CheckUsernameContext checkUsernamePO2CheckUsernameContext(CheckUsernamePO checkUsernamePO);

    /**
     * CheckAnswerPO转CheckAnswerContext
     *
     * @param checkAnswerPO
     * @return
     */
    CheckAnswerContext checkAnswerPO2CheckAnswerContext(CheckAnswerPO checkAnswerPO);

    /**
     * ResetPasswordPO转ResetPasswordContext
     *
     * @param resetPasswordPO
     * @return
     */
    ResetPasswordContext resetPasswordPO2ResetPasswordContext(ResetPasswordPO resetPasswordPO);

    /**
     * ChangePasswordPO转ChangePasswordContext
     *
     * @param changePasswordPO
     * @return
     */
    ChangePasswordContext changePasswordPO2ChangePasswordContext(ChangePasswordPO changePasswordPO);

    UserInfoVO assembleUserInfoVO(RPanUser entity, RPanUserFile rPanUserFile);
}
