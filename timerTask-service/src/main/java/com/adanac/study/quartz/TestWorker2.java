package com.adanac.study.quartz;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TestWorker2 extends QuartzJobBean {
    private int timeout;
    //调度工厂实例化后，经过timeout时间开始执行调度
    public void setTimeout(int timeout){
        this.timeout = timeout;
    }


    /**
     * 要调度的具体任务
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时任务2 ---> "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
