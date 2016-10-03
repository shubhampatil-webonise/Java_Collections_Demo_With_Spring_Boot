package org.webonise.springboot;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

public class Task implements Comparable<Task> {

    private final UUID taskId;
    private int taskPriority;

    Task(int taskPriority) {
        this.taskId = UUID.randomUUID();
        this.taskPriority = taskPriority;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void execute() {
        System.out.println("Executing Task : " + taskId);
    }

    @Override
    public int compareTo(Task task) {

        if (this.getTaskPriority() > task.getTaskPriority()) {
            return 1;
        } else if (this.getTaskPriority() < task.getTaskPriority()) {
            return -1;
        } else {
            return 0;
        }
    }


    @Override
    public boolean equals(Object taskToCompare) {
        if (this == taskToCompare) return true;

        if (taskToCompare instanceof Task) {

            Task task = (Task) taskToCompare;

            EqualsBuilder equalsBuilder = new EqualsBuilder();
            equalsBuilder.append(this.getTaskId(), task.getTaskId());
            equalsBuilder.append(this.getTaskPriority(), task.getTaskPriority());

            return equalsBuilder.isEquals();
        }

        return false;
    }

    @Override
    public int hashCode() {

        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        hashCodeBuilder.append(this.getTaskId());
        hashCodeBuilder.append(this.getTaskPriority());

        return hashCodeBuilder.toHashCode();
    }
}
