package com.imooc.pan.server.modules.file.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.pan.server.modules.file.entity.RPanFileChunk;
import com.imooc.pan.server.modules.file.mapper.RPanFileChunkMapper;
import com.imooc.pan.server.modules.file.service.IRPanFileChunkService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件分片信息表 服务实现类
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
@Service
public class RPanFileChunkServiceImpl extends ServiceImpl<RPanFileChunkMapper, RPanFileChunk> implements IRPanFileChunkService {

}
