package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task16.*;

/*
Задача 16 на паттерн «Приспособленец» (Flyweight)
Задача:
Реализуйте систему отображения символов текста на экране.

Каждый символ (Character) имеет внутреннее состояние (код символа) и внешнее состояние (координаты x, y и стиль).
Используйте фабрику адаптеров, чтобы повторно использовать объекты символов и уменьшить количество объектов, создаваемых в системе.
Подсказка:
Внешнее состояние передаётся в метод render(), а внутреннее хранится внутри объектов, создаваемых фабрикой.
*/

@TaskDescription(taskNumber = 16, taskDescription = "Задача «Паттерн «Приспособленец» (Flyweight)»")

public class Task16 implements Solution {

    @Override
    public void execute() {
        CharacterFactory factory = new CharacterFactory();

        MyCharacter characterA = factory.getCharacter('A');
        MyCharacter characterB = factory.getCharacter('B');
        MyCharacter characterC = factory.getCharacter('C');
        MyCharacter characterA2 = factory.getCharacter('A');

        characterA.render(1, 2, "Bold");
        characterB.render(2, 3, "Italic");
        characterC.render(3, 4, "Underline");
        characterA2.render(4, 5, "Bold");
    }
}
