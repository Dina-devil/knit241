package org.knit.solutions.task17;
import java.util.Random;

public class MontyHall {

    private final Random random = new Random();

    public int[] simulate(int simulations) {
        int winsWhenSwitched = 0;
        int winsWhenStayed = 0;

        for (int i = 0; i < simulations; i++) {
            int prizeDoor = random.nextInt(3); // за какой дверью приз
            int playerChoice = random.nextInt(3); // выбор игрока

            int hostOpens;
            do {
                hostOpens = random.nextInt(3);
            } while (hostOpens == prizeDoor || hostOpens == playerChoice);

            int switchChoice = 3 - playerChoice - hostOpens;

            if (switchChoice == prizeDoor) {
                winsWhenSwitched++;
            }
            if (playerChoice == prizeDoor) {
                winsWhenStayed++;
            }
        }
        return new int[]{winsWhenSwitched, winsWhenStayed};
    }
}

