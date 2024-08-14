package com.imooc.pan.server.modules.share.context;

import com.imooc.pan.server.modules.share.entity.RPanShare;
import lombok.Data;

import java.io.Serializable;

/**
 * 校验分享码上下文实体对象
 */
@Data
public class CheckShareCodeContext implements Serializable {


    private static final long serialVersionUID = 7406791699745296567L;

    /**
     * 分享ID
     */
    private Long shareId;

    /**
     * 分享码
     */
    private String shareCode;

    /**
     * 对应的分享实体
     */
    private RPanShare record;

}
