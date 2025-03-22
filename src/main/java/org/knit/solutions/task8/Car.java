package org.knit.solutions.task8;

public class Car {
    private final String name;

    public Car(String name) {
        this.name = name;
    }

    public synchronized void pass(TrafficLight trafficLight) {
        trafficLight.waitForGreen();
        System.out.println("Машина " + name + " проехала перекресток");
    }
}