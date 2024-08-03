package com.imooc.pan.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.concurrent.ScheduledFuture;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleTaskHolder implements Serializable {

    /**
     * 执行任务实体
     */
    private ScheduleTask scheduleTask;

    /**
     * 执行任务的结果实体
     */
    private ScheduledFuture scheduledFuture;

}
