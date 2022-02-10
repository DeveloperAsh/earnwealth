package com.pune.earnwealth.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskDeatils {

    @SerializedName("task_id")
    @Expose
    private Integer taskId;
    @SerializedName("task_name")
    @Expose
    private String taskName;
    @SerializedName("task_description")
    @Expose
    private String taskDescription;
    @SerializedName("assign_to")
    @Expose
    private String assignTo;
    @SerializedName("task_category")
    @Expose
    private String taskCategory;
    @SerializedName("task_efforts")
    @Expose
    private String taskEfforts;
    @SerializedName("current_status")
    @Expose
    private String currentStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("task_duedate")
    @Expose
    private String taskDuedate;
    @SerializedName("task_action")
    @Expose
    private String taskAction;
    @SerializedName("task_priority")
    @Expose
    private String taskPriority;
    @SerializedName("task_business_priority")
    @Expose
    private String taskBusinessPriority;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getTaskEfforts() {
        return taskEfforts;
    }

    public void setTaskEfforts(String taskEfforts) {
        this.taskEfforts = taskEfforts;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTaskDuedate() {
        return taskDuedate;
    }

    public void setTaskDuedate(String taskDuedate) {
        this.taskDuedate = taskDuedate;
    }

    public String getTaskAction() {
        return taskAction;
    }

    public void setTaskAction(String taskAction) {
        this.taskAction = taskAction;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskBusinessPriority() {
        return taskBusinessPriority;
    }

    public void setTaskBusinessPriority(String taskBusinessPriority) {
        this.taskBusinessPriority = taskBusinessPriority;
    }

}