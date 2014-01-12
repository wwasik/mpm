package com.efektech.myprojectmanager.model;

import java.util.Date;
import java.util.List;

/**
 * @author dom
 */
public class Task {
    private Date start;
    private Date stop;
    private List<Job> jobs;
    private Task previous;
    private TaskType type;
    private String description;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStop() {
        return stop;
    }

    public void setStop(Date stop) {
        this.stop = stop;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Task getPrevious() {
        return previous;
    }

    public void setPrevious(Task previous) {
        this.previous = previous;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
        
    

}
