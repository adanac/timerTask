package com.adanac.study;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzDemo {

    public static void main(String[] args) {
        try {
            new QuartzDemo().quartzTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void quartzTest() throws InterruptedException {
        try {
            //获取调度器
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            //创建任务器：定义任务细节
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
            ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever();
            //定义触发器
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "simpleTriggerGroup")
                    .withSchedule(scheduleBuilder).startNow().build();

            //将任务和触发器注册到调度器中
            scheduler.scheduleJob(jobDetail, trigger);
            Thread.sleep(1000 * 30);
//            scheduler.shutdown();
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}