package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task17.*;

/*
Описание:
Парадокс Монти Холла — это задача из теории вероятностей, основанная на популярном шоу "Let's Make a Deal".
Правила такие:
Участник выбирает одну из трёх дверей.
Ведущий, который знает, что за дверями, открывает одну из оставшихся дверей, за которой нет приза.
Участнику предлагается либо сменить выбор на оставшуюся дверь, либо оставить свой выбор неизменным.
Вопрос:
Реализуйте симуляцию этой игры на Java, чтобы проверить, что лучше: менять выбор или оставаться при своём?.
Программа должна провести большое количество экспериментов (например, 1000000) и показать вероятность выигрыша в каждом из двух случаев.
Ожидаемый результат:
При достаточно большом количестве игр (например, 1 миллион):
Вероятность выигрыша при переключении: около 66.66% (или 2/3).
Вероятность выигрыша без переключения: около 33.33% (или 1/3).
 */

@TaskDescription(taskNumber = 17, taskDescription = "Парадокс Монти Холла")

public class Task17 implements Solution {

    @Override
    public void execute() {
        int simulations = 1_000_000;
        MontyHall montyHall = new MontyHall();
        int[] results = montyHall.simulate(simulations);

        double switchWinRate = (double) results[0] / simulations * 100;
        double stayWinRate = (double) results[1] / simulations * 100;

        System.out.printf("Вероятность выигрыша при СМЕНЕ двери: %.2f%%\n", switchWinRate);
        System.out.printf("Вероятность выигрыша при ОСТАВЛЕНИИ выбора: %.2f%%\n", stayWinRate);
    }
}