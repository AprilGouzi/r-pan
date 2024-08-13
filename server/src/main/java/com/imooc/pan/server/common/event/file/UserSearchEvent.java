package com.imooc.pan.server.common.event.file;

/**
 * @author AprilGouzi
 * version 1.0
 */

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * 用户搜索事件
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserSearchEvent extends ApplicationEvent {

    private String keyword;

    private Long userId;

    public UserSearchEvent(Object source, String keyword, Long userId) {
        super(source);
        this.keyword = keyword;
        this.userId = userId;
    }

}
