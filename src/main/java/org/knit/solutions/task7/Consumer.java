package org.knit.solutions.task7;

public class Consumer implements Runnable {
    Store store;
    public Consumer(Store store) {
        this.store = store;
    }
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                store.consume();
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
