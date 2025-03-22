package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task8.Car;
import org.knit.solutions.task8.TrafficLight;

/* 2.8 Задача «Перекресток: светофор и машины» 🚦🚗
📌 Описание:
На перекрестке светофор управляет движением:

Красный свет – машины стоят (wait()).
Зеленый свет – машины едут (notifyAll()).
Светофор переключается каждые 5 секунд.
🔹 Что нужно реализовать?
✔ Поток "Светофор" изменяет цвет и отправляет notifyAll().
✔ Потоки "Машина" ждут wait(), если красный свет. */

@TaskDescription(taskNumber = 8, taskDescription = "Задача «Перекресток: светофор и машины»")

public class Task8 implements Solution {
    @Override
    public void execute() {
        TrafficLight trafficLight = new TrafficLight();

        Thread threadTrafficLight = new Thread(() -> {
            while (trafficLight.changeColor()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        Thread threadCar = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                Car car = new Car(String.valueOf(i));
                car.pass(trafficLight);
                try {
                    Thread.sleep(900);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        threadTrafficLight.start();
        threadCar.start();

        try {
            threadTrafficLight.join();
            threadCar.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Программа завершена.");
    }
}
