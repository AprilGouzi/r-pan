package com.imooc.pan.schedule.test;

import com.imooc.pan.schedule.ScheduleManager;
import com.imooc.pan.schedule.test.config.ScheduleTestConfig;
import com.imooc.pan.schedule.test.task.SimpleScheduleTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author AprilGouzi
 * version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)//让测试运行于Spring测试环境
@ContextConfiguration(classes = ScheduleTestConfig.class)
public class ScheduleTashTest {

    @Autowired
    private ScheduleManager manager;

    @Autowired
    private SimpleScheduleTask scheduleTask;

    @Test
    public void testRunScheduleTask() throws Exception {
        String cron = "0/5 * * * * ? ";

        String key = manager.startTask(scheduleTask, cron);

        Thread.sleep(10000);

        cron = "0/1 * * * * ? ";

        key = manager.changeTask(key, cron);

        Thread.sleep(10000);

        manager.stopTask(key);
    }

}
