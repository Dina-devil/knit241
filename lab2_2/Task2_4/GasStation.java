package org.dina.lab2_2.Task2_4;

import java.util.concurrent.Semaphore;


/* 2.4 Задача «Автозаправочная станция» 🚗⛽
📌 Описание:
На автозаправочной станции только 2 колонки. Если все заняты, машины ждут в очереди.
Когда колонка освобождается, следующий автомобиль из очереди начинает заправку.

🔹 Что нужно реализовать?
✔ Использовать wait() и notify() для ожидания и освобождения заправки.
✔ Поток "Машина" ждет, если все колонки заняты.
✔ Поток "Машина" заправляется, затем освобождает колонку. */


// semaphore - механизм, который ограничивает количество потоков, имеющих доступ к ресурсу.

public class GasStation {
    private final Semaphore semaphore;

    public GasStation(int count) {
        this.semaphore = new Semaphore(count);
    }

    public void Refuel(String carName) {
        try {
            System.out.println(carName + " ждет заправки");
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(carName + " заправилась и уехала");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
