package org.webonise.springboot;

public enum SchedularType {

    FIFO, LIFO, PRIORITY_BASED;

    static SchedularType getSchedularBasedOnString(String choice) {

        for (SchedularType schedular : SchedularType.values()) {
            if (schedular.name().equalsIgnoreCase(choice)) return SchedularType.valueOf(schedular.name());
        }
        throw new IllegalArgumentException("Schedular Not found !");
    }
}
