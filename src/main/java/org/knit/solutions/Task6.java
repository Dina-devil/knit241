package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task6.Railway;

/* 2.6 Задача «Железнодорожный переезд» 🚆🚗
📌 Описание:
Есть автомобили и поезд.

Если поезд приближается, машины останавливаются и ждут.
После того, как поезд проедет, машины продолжают движение.
🔹 Что нужно реализовать?
✔ Поток "Поезд" останавливает автомобили (wait()).
✔ Поток "Поезд" сообщает о завершении (notifyAll()).
✔ Машины ждут, если поезд едет, и продолжают движение после notifyAll(). */

@TaskDescription(taskNumber = 6, taskDescription = "Задача «Железнодорожный переезд»")

public class Task6 implements Solution{
    @Override
    public void execute() {
    Railway railway = new Railway();
    final int CAR_COUNT = 20;

    Thread threadCars = new Thread(() -> {
        for (int i = 1; i <= CAR_COUNT; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            railway.passCar(i + " машина");
        }
    });

    Thread threadTrain = new Thread(() -> {
        try {
            Thread.sleep(500);
            railway.trainIsPassing();
            Thread.sleep(2000);
            railway.trainPassed();
            Thread.sleep(500);
            railway.trainIsPassing();
            Thread.sleep(2000);
            railway.trainPassed();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    });

    threadCars.start();
    threadTrain.start();

    try {
        threadCars.join();
        threadTrain.join();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }

    System.out.println("Программа завершена.");
}
}
