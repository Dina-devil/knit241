package org.dina.lab2_2.Task2_7;

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

class Store {
    private int products = 0;
    private static final int MAX_PRODUCTS = 5;

    public synchronized void produce() throws InterruptedException {
        while (products >= MAX_PRODUCTS) {
            System.out.println("Склад полон. Производитель ждет.");
            wait();
        }
        products++;
        System.out.println("Производитель добавил товар. Товаров на складе: " + products);
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (products == 0) {
            System.out.println("Склад пуст. Потребитель ждет.");
            wait();
        }
        products--;
        System.out.println("Потребитель купил товар. Товаров на складе: " + products);
        notify();
    }
}

