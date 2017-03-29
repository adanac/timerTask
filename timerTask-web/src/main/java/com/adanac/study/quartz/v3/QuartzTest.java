package com.adanac.study.quartz.v3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuartzTest {
	public static void main(String[] args) {
		System.out.println("Test Start......");
		ApplicationContext context = new ClassPathXmlApplicationContext("config/quartz.xml");
		System.out.println("Test End......");
	}
}
