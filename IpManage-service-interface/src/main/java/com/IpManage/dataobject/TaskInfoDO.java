package com.IpManage.dataobject;

import java.io.Serializable;

public class TaskInfoDO implements Serializable {
    private String task_id;
    private String jobName;//定时任务名称
    private String triggerName;
    private String jobGroupName;
    private String triggerGroupName;
    private String taskMainUrl; //定时任务启动方法
    private String status;//是否启动
    private String frequency;//频率
    private String insertTime;//添加时间

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public String getTriggerGroupName() {
        return triggerGroupName;
    }

    public void setTriggerGroupName(String triggerGroupName) {
        this.triggerGroupName = triggerGroupName;
    }

    public String getTaskMainUrl() {
        return taskMainUrl;
    }

    public void setTaskMainUrl(String taskMainUrl) {
        this.taskMainUrl = taskMainUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "TaskInfoDO{" +
                "task_id='" + task_id + '\'' +
                ", jobName='" + jobName + '\'' +
                ", triggerName='" + triggerName + '\'' +
                ", jobGroupName='" + jobGroupName + '\'' +
                ", triggerGroupName='" + triggerGroupName + '\'' +
                ", taskMainUrl='" + taskMainUrl + '\'' +
                ", status='" + status + '\'' +
                ", frequency='" + frequency + '\'' +
                '}';
    }
}
