package org.webonise.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchedularFactory {

    @Autowired
    FIFOSchedular fifoSchedular;

    @Autowired
    LIFOSchedular lifoSchedular;

    @Autowired
    PriorityBasedSchedular priorityBasedSchedular;

    public TaskSchedular getTaskSchedular(SchedularType schedular, int queueSize) {

        switch (schedular) {
            case FIFO:
                fifoSchedular.setSchedularParameters(queueSize);
                return fifoSchedular;

            case LIFO:
                lifoSchedular.setSchedularParameters(queueSize);
                return lifoSchedular;

            case PRIORITY_BASED:
                priorityBasedSchedular.setSchedularParameters(queueSize);
                return priorityBasedSchedular;

            default:
                throw new IllegalArgumentException("Invalid Schedular Type !");
        }
    }
}
