package com.imooc.pan.server.modules.user;

import cn.hutool.core.lang.Assert;
import com.imooc.pan.core.exception.RPanBusinessException;
import com.imooc.pan.core.utils.JwtUtil;
import com.imooc.pan.server.RPanServerLauncher;
import com.imooc.pan.server.modules.user.constants.UserConstants;
import com.imooc.pan.server.modules.user.context.UserLoginContext;
import com.imooc.pan.server.modules.user.context.UserRegisterContext;
import com.imooc.pan.server.modules.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AprilGouzi
 * version 1.0
 * 用户模块单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RPanServerLauncher.class)
@Transactional //做数据回滚，不会新增到数据库
public class UserTest {

    @Autowired
    private IUserService iUserService;

    /**
     * 测试功能注册用户信息
     */
    @Test
    public void testRegisterUser() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register.longValue() > 0L);
    }

    @Test(expected = RPanBusinessException.class)
    public void testRegisterDuplicateUsername() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register.longValue() > 0L);
        iUserService.register(context);
    }

    /**
     * 测试登陆成功
     */
    @Test
    public void loginSuccess() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        UserLoginContext userLoginContext = createUserLoginContext();
        String accessToken = iUserService.login(userLoginContext);

        Assert.isTrue(StringUtils.isNotBlank(accessToken));
    }

    /**
     * 测试登录失败：用户名不正确
     */
    @Test(expected = RPanBusinessException.class)
    public void wrongUsername() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        UserLoginContext userLoginContext = createUserLoginContext();
        userLoginContext.setUsername(userLoginContext.getUsername() + "_change");
        iUserService.login(userLoginContext);
    }

    /**
     * 测试登录失败：密码不正确
     */
    @Test(expected = RPanBusinessException.class)
    public void wrongPassword() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        UserLoginContext userLoginContext = createUserLoginContext();
        userLoginContext.setPassword(userLoginContext.getPassword() + "_change");
        iUserService.login(userLoginContext);
    }

    /**
     * 用户成功登出
     */
    @Test
    public void exitSuccess() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        UserLoginContext userLoginContext = createUserLoginContext();
        String accessToken = iUserService.login(userLoginContext);

        Assert.isTrue(StringUtils.isNotBlank(accessToken));

        Long userId = (Long) JwtUtil.analyzeToken(accessToken, UserConstants.LOGIN_USER_ID);

        iUserService.exit(userId);
    }



    /*****************************************************private******************************************/
    private static final String USERNAME = "imooc";
    private static final String PASSWORD = "123456789";
    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";

    /**
     * 构建注册用户上下文信息
     *
     * @return
     */
    private UserRegisterContext createUserRegisterContext() {
        UserRegisterContext context = new UserRegisterContext();
        context.setUsername(USERNAME);
        context.setPassword(PASSWORD);
        context.setQuestion(QUESTION);
        context.setAnswer(ANSWER);
        return context;
    }


    private UserLoginContext createUserLoginContext() {
        UserLoginContext userLoginContext = new UserLoginContext();
        userLoginContext.setUsername(USERNAME);
        userLoginContext.setPassword(PASSWORD);
        return userLoginContext;
    }

}
