## 启动Web项目后的定时任务
com.adanac.study.timetask.TestEverySecond 每隔 5秒钟 (1/5 * * * * ?)执行 testTask()方法一次
com.adanac.study.quartz.TestWorker1 每隔 1秒钟 (*/1 * * * * ?)执行一次定时任务
com.adanac.study.quartz.TestWorker2 每隔 2秒钟 (*/2 * * * * ?)执行一次定时任务
## 不启动Web项目
- 普通thread com.adanac.study.v1.Task1
- Timer类调度 com.adanac.study.v2.Task2
- ScheduledExecutorService线程池的方式 com.adanac.study.v3.Task3
- com.adanac.study.quartz.v3.QuartzTest 根据 classpath:config/quartz.xml 中的配置去执行
