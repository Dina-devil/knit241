package org.knit.solutions.task7;

public class Store {
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

