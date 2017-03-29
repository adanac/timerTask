## 启动Web项目后的定时任务
每隔 5秒钟 (1/5 * * * * ?)执行com.adanac.study.timetask.TestEverySecond的 testTask()方法一次
## 不启动Web项目
- 普通thread com.adanac.study.v1.Task1
- Timer类调度 com.adanac.study.v2.Task2
- ScheduledExecutorService线程池的方式 com.adanac.study.v3.Task3