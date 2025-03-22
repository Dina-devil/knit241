package org.knit.solutions.task8;

public class TrafficLight {
    private boolean isRed = true;
    private int cyclesCompleted = 0;
    private final int MAX_CYCLES = 5; // Максимальное количество циклов светофора

    public synchronized boolean isRed() {
        return isRed;
    }

    public synchronized void waitForGreen() {
        while (isRed) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public synchronized boolean changeColor() {
        isRed = !isRed;
        System.out.println((isRed ? "Красный" : "Зеленый") + " свет включен.");
        notifyAll();
        cyclesCompleted++;
        return cyclesCompleted < MAX_CYCLES;
    }
}
