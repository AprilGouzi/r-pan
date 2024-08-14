package com.imooc.pan.server.modules.log.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.pan.server.modules.log.entity.RPanErrorLog;
import com.imooc.pan.server.modules.log.mapper.RPanErrorLogMapper;
import com.imooc.pan.server.modules.log.service.IErrorLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 错误日志表 服务实现类
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
@Service
public class ErrorLogServiceImpl extends ServiceImpl<RPanErrorLogMapper, RPanErrorLog> implements IErrorLogService {

}
