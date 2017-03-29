package com.adanac.study;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Hello world!
 *
 */
public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        // TODO Auto-generated method stub
        System.out.println("开启了定时任务");

    }
}
