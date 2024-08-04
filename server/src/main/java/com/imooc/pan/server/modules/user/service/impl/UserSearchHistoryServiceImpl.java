package com.imooc.pan.server.modules.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.pan.server.modules.user.entity.RPanUserSearchHistory;
import com.imooc.pan.server.modules.user.mapper.RPanUserSearchHistoryMapper;
import com.imooc.pan.server.modules.user.service.IUserSearchHistoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户搜索历史表 服务实现类
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
@Service(value = "userSearchHistoryService")
public class UserSearchHistoryServiceImpl extends ServiceImpl<RPanUserSearchHistoryMapper, RPanUserSearchHistory> implements IUserSearchHistoryService {

}
