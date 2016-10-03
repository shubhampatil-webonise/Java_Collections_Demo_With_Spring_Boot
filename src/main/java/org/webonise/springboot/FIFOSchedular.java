package org.webonise.springboot;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class FIFOSchedular implements TaskSchedular {
    private static final Logger logger = Logger.getLogger(FIFOSchedular.class.getName());

    private List<Task> taskList;
    private Map<Integer, TaskSet> taskGroupsBasedOnPriority;
    private int queueCapacity;

    public void setSchedularParameters(int queueCapacity) {
        this.queueCapacity = queueCapacity;
        this.taskList = new ArrayList<Task>(queueCapacity);
        this.taskGroupsBasedOnPriority = new HashMap<Integer, TaskSet>();
    }

    @Override
    public void addTask(Task task) {

        if (taskList.size() < queueCapacity) {
            addTaskToList(task);
            addTaskToTaskMapBasedOnPriority(task);
            printTaskList();
        } else
            logger.info("Error : Can't add new task. Task List is full.\n");
    }

    @Override
    public void printTaskList() {
        logger.info("Tasks currently present in Task List :\n");

        for (Task task : taskList) {
            logger.info("Task Id :" + String.valueOf(task.getTaskId()) + ", Priority :" + String.valueOf(task.getTaskPriority()) + "\n");
        }
    }

    @Override
    public Task fetchTaskForExecution() {

        if (!taskList.isEmpty()) {
            Task task = taskList.get(0);
            taskList.remove(0);
            return task;
        }
        throw new IllegalArgumentException("Task List is Empty");
    }

    private void addTaskToList(Task task) {
        taskList.add(task);
        logger.info("New task added to Task List.\n");
    }

    private void addTaskToTaskMapBasedOnPriority(Task task) {

        if (taskGroupsBasedOnPriority.containsKey(task.getTaskPriority())) {
            taskGroupsBasedOnPriority.get(task.getTaskPriority()).addToTaskSet(task.getTaskId());
        } else {
            TaskSet taskSetWithSamePriority = new TaskSet();
            taskSetWithSamePriority.addToTaskSet(task.getTaskId());
            taskGroupsBasedOnPriority.put(task.getTaskPriority(), taskSetWithSamePriority);
        }
    }

    @Override
    public void printTaskGroups() {

        if (taskGroupsBasedOnPriority.isEmpty()) {
            logger.info("Map of Task Groups is empty !\n");
            return;
        }

        Iterator<Integer> mapIterator = taskGroupsBasedOnPriority.keySet().iterator();

        while (mapIterator.hasNext()) {
            Integer taskPriority = mapIterator.next();
            System.out.println("Priority : " + taskPriority + "\n");
            taskGroupsBasedOnPriority.get(taskPriority).printTaskSet();
            logger.info("=============================================\n\n");
            System.out.println();
        }
    }
}
