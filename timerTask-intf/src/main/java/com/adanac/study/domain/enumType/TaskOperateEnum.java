package com.adanac.study.domain.enumType;

import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: taoyong1
 * Date: 2016/5/31
 * Time: 13:43
 * Description: 定时任务操作枚举
 */
public enum  TaskOperateEnum {
    START_TASK(1,"start","开启定时任务"),
    START_ONECE_TASK(2,"startOnece","执行一次定时任务"),
    STOP_TASK(3,"stop","暂停定时任务");
    public int code;
    public String order;
    private String info;

    TaskOperateEnum(int code, String order, String info) {
        this.code = code;
        this.order = order;
        this.info = info;
    }
    public static int getCodeByOrder(String order){
        if(StringUtils.isBlank(order))
            return 0;
        for(TaskOperateEnum taskOperateEnum : TaskOperateEnum.values()){
            if(order.equals(taskOperateEnum.order)){
                return taskOperateEnum.code;
            }
        }
        return 0;
    }
}
