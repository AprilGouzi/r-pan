package com.imooc.pan.server.modules.share.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.pan.server.modules.share.entity.RPanShare;
import com.imooc.pan.server.modules.share.vo.RPanShareUrlListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户分享表 Mapper 接口
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
public interface RPanShareMapper extends BaseMapper<RPanShare> {

    /**
     * 查询用户的分享列表
     *
     * @param userId
     * @return
     */
    List<RPanShareUrlListVO> selectShareVOListByUserId(@Param("userId") Long userId);
}
