package org.knit.solutions.task5;

import java.util.LinkedList;
import java.util.Queue;

public class Restaraunt {
    private final int CAPACITY = 3;
    private final Queue<String> mealsQueue = new LinkedList<>();

    public synchronized void cook(String name) {
        while (mealsQueue.size() >= CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        mealsQueue.add(name);
        System.out.println("Повар приготовил блюдо " + name);
        notify();
    }

    public synchronized void serve() {
        while (mealsQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Официант подал блюдо " + mealsQueue.poll());
        notify();
    }
}
