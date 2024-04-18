package com.alec.spring.rest.rest_api.model;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


public class Task implements Comparable<Task> {
    private String caption;
    private int priority;
    private String description;
    private LocalDate deadline;
    private LocalDate complete;
    private Status status;

    public Task() {
    }

    public Task(String caption, String description, LocalDate deadline, Status status, int priority) {
        this.caption = caption;
        this.priority = priority;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        setComplete(status);
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getComplete() {
        return complete;
    }

    public LocalDate setComplete(Status status) {
        switch (status) {

            case NEW:
            case IN_PROGRESS:
                complete = LocalDate.of(01, 01, 01);
                break;
            case DONE:
                complete = LocalDate.now();
        }
        return complete;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {

        this.status = status;
        setComplete(status);
    }

    @Override
    public String toString() {
        return "Task{" +

                ", caption='" + caption + '\'' +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", complete=" + complete +
                ", status=" + status +
                '}';
    }

    @Override
    public int compareTo(Task task) {
        return task.getCaption().compareTo(caption);
    }
}

