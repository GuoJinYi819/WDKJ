package com.wd.tech.bean.wybean.beanusertasklist;

import java.io.Serializable;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/5/1 16:24
 * @classname :ResultBean
 */
public class ResultBean implements Serializable {
    private int status;
    private int taskId;
    private int taskIntegral;
    private String taskName;
    private int taskType;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskIntegral() {
        return taskIntegral;
    }

    public void setTaskIntegral(int taskIntegral) {
        this.taskIntegral = taskIntegral;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }
}
