package org.webonise.springboot;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Application {
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    @Autowired
    private Scanner scanner;

    @Autowired
    private SchedularFactory schedularFactory;

    @Autowired
    private PriorityGenerator priorityGenerator;

    private String typeOfSchedular;
    private TaskSchedular taskSchedular;
    private int queueCapacity;

    public void start() {
        try {
            displayMainMenuAndGetSchedularType();
            getInputAndSetTaskListCapacity();
            selectTaskSchedularBasedOnType();
            selectSchedularOperation();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayMainMenuAndGetSchedularType() {
        logger.info("Select a schedular ...\n");
        logger.info("1. FIFO Schedular (Enter FIFO) \n");
        logger.info("2. LIFO Schedular (Enter LIFO) \n");
        logger.info("3. Priority Based Schedular (Enter PRIORITY_BASED) \n");
        logger.info("Input :");

        typeOfSchedular = scanner.next();
    }

    private void getInputAndSetTaskListCapacity() {
        logger.info("Enter Task Queue size : ");
        queueCapacity = scanner.nextInt();
    }

    private void selectTaskSchedularBasedOnType() {
        try {
            SchedularType schedular = SchedularType.getSchedularBasedOnString(typeOfSchedular);
            taskSchedular = schedularFactory.getTaskSchedular(schedular, queueCapacity);
            logger.info("New " + schedular.name() + " schedular created !\n");

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void selectSchedularOperation() {

        boolean continueLooping = true;

        while (continueLooping) {

            displaySchedularSpecificMenu();
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        taskSchedular.addTask(new Task(priorityGenerator.generatePriority()));
                        break;

                    case 2:
                        taskSchedular.printTaskList();
                        break;

                    case 3:
                        Task task = taskSchedular.fetchTaskForExecution();
                        task.execute();
                        break;

                    case 4:
                        taskSchedular.printTaskGroups();
                        break;

                    case 5:
                        continueLooping = false;
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid Choice");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void displaySchedularSpecificMenu() {

        logger.info("1. Add new Task to queue.\n");
        logger.info("2. Print current task queue.\n");
        logger.info("3. Fetch task from Queue to Execute.\n");
        logger.info("4. Print task groups based on priority.\n");
        logger.info("5. Stop Schedular.\n");
        logger.info("Input :");
    }
}
