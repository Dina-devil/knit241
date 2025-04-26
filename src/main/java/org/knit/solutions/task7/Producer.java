package org.knit.solutions.task7;

public class Producer implements Runnable {
    Store store;
    public Producer(Store store) {
        this.store = store;
    }
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                store.produce();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

