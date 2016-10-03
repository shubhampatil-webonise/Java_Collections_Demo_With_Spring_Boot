package org.webonise.springboot;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.UUID;

@Component
public class TaskSet {

    private HashSet<UUID> hashSet;

    TaskSet() {
        hashSet = new HashSet<UUID>();
    }

//    @Autowired
//    private HashSet<UUID> hashSet;
//
//    @Autowired
//    public void setHashSet(HashSet<UUID> hashSet) {
//        this.hashSet = hashSet;
//    }

    public void addToTaskSet(UUID taskId) {
        hashSet.add(taskId);
    }

    public void printTaskSet() {
        Iterator<UUID> setIterator = hashSet.iterator();

        while (setIterator.hasNext()) {
            UUID taskId = setIterator.next();
            System.out.println(" Task Id : " + taskId + "\n");
        }
    }
}
