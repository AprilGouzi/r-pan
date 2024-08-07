package com.imooc.pan.server.modules.file.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.pan.server.modules.file.context.QueryFileListContext;
import com.imooc.pan.server.modules.file.entity.RPanUserFile;
import com.imooc.pan.server.modules.file.vo.RPanUserFileVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户文件信息表 Mapper 接口
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
public interface RPanUserFileMapper extends BaseMapper<RPanUserFile> {

    List<RPanUserFileVO> selectFileList(@Param("param") QueryFileListContext context);
}
