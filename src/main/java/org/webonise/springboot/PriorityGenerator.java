package org.webonise.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PriorityGenerator {

    @Autowired
    Random random;

    public int generatePriority() {

        int maxPriority = 20;
        int minPriority = 0;

        return random.nextInt((maxPriority - minPriority) + 1) + minPriority;
    }
}
