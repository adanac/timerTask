package com.adanac.study.timetask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by allen on 2017/3/28.
 */
@Component("taskJob")
public class TestEverySecond {
    @Scheduled(cron = "0 1/10 * * * ?")
    public void testTask(){
        System.out.println(System.currentTimeMillis());
    }
}
