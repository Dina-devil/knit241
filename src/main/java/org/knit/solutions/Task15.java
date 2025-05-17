package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task15.*;

/*
Задача 15 на паттерн Прокси (Proxy)
Задача:
Создайте удаленную библиотеку изображений.

При запросе изображения оно загружается с сервера (имитация с помощью задержки в коде).
Картинка хранится как объект класса RealImage.
Прокси-объектImageProxy должен лениво загружать изображение и кэшировать его для повторного использования.
*/

@TaskDescription(taskNumber = 15, taskDescription = "Задача «паттерн Прокси (Proxy)»")

public class Task15 implements Solution {

    @Override
    public void execute() {
        Image image1 = new ImageProxy("image1.jpg");
        Image image2 = new ImageProxy("image2.jpg");

        image1.display(); // загружается с сервера и отображается
        image1.display(); // отображается из кэша
        image2.display(); // загружается с сервера и отображается
    }
}