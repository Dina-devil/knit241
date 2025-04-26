package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task7.Consumer;
import org.knit.solutions.task7.Producer;
import org.knit.solutions.task7.Store;

/* 2.7 Задача «Производитель-Потребитель с ограничением» 🏭📦
📌 Описание:

Производитель создает товары (максимум 5).
Потребитель забирает товары.
Если товаров нет, потребитель ждет (wait()).
Если товаров максимум, производитель ждет (wait()).
🔹 Что нужно реализовать?
✔ wait() – если товаров нет или склад заполнен.
✔ notify() – пробуждение потока, когда изменяется состояние склада. */


//wait() – переводит поток в состояние ожидания до вызова notify() или notifyAll().
//notify() – пробуждает один ожидающий поток.
//notifyAll() – пробуждает все ожидающие потоки.

@TaskDescription(taskNumber = 7, taskDescription = "Задача «Производитель-Потребитель с ограничением»")

public class Task7 implements Solution {
    @Override
    public void execute() {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
