package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task13.*;

import java.util.Arrays;

/*
3. Паттерн Command (Команда)
Задача 13: Реализация системы команд для управления умным домом

Вы разрабатываете систему управления "умным домом", где пользователь может выполнять различные команды, такие как включение/выключение света, управление телевизором и кондиционером.

Требуется:

Создать интерфейс Command, который определяет метод execute().
Реализовать классы команд:
LightOnCommand – включает свет.
LightOffCommand – выключает свет.
TVOnCommand – включает телевизор.
TVOffCommand – выключает телевизор.
Создать класс RemoteControl, который хранит команду и позволяет её выполнить.
Реализовать механизм отмены последней команды (undo).
Пример работы:

Пользователь нажимает кнопку "Включить свет" – выполняется LightOnCommand.
Затем нажимает "Выключить телевизор" – выполняется TVOffCommand.
Пользователь нажимает "Отменить последнюю команду" – телевизор снова включается.
Дополнительно: Реализуйте поддержку макрокоманд (например, кнопка "Спокойной ночи" выключает все приборы разом).
*/

@TaskDescription(taskNumber = 13, taskDescription = "Задача «Реализация системы команд для управления умным домом»")

public class Task13 implements Solution{

    @Override
    public void execute() {
        Light light = new Light();
        TV tv = new TV();
        RemoteControl remote = new RemoteControl();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        Command tvOn = new TVOnCommand(tv);
        Command tvOff = new TVOffCommand(tv);

        remote.setCommand(lightOn);
        remote.pressButton();

        remote.setCommand(tvOn);
        remote.pressButton();

        Command goodNight = new MacroCommand(Arrays.asList(lightOff, tvOff));
        remote.setCommand(goodNight);
        remote.pressButton();

        remote.undo();
    }
}

