package com.imooc.pan.server.modules.file.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.pan.server.modules.file.entity.RPanFile;
import com.imooc.pan.server.modules.file.mapper.RPanFileMapper;
import com.imooc.pan.server.modules.file.service.IFileService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物理文件信息表 服务实现类
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
@Service
public class FileServiceImpl extends ServiceImpl<RPanFileMapper, RPanFile> implements IFileService {

}
