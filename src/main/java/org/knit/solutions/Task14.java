package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task14.*;

/*
Задача 14 на паттерн Визитер (Visitor)
Задача:
Создайте систему управления файловыми объектами.
Существует три типа файловых объектов:

Файлы,
Папки,
Ссылки (ярлыки).
Требуется реализовать два посетителя:

Сканер вирусов, который проверяет каждый файл на наличие вирусов.
Анализатор размера, который подсчитывает общий размер файлов (ссылки не учитываются).
*/

@TaskDescription(taskNumber = 14, taskDescription = "Задача «Паттерн Визитер (Visitor)»")

public class Task14 implements Solution {

    @Override
    public void execute() {
        File file1 = new File("file1.txt", 500);
        File file2 = new File("file2.txt", 1000);
        Folder folder = new Folder("MyFolder");
        folder.addElement(file1);
        folder.addElement(file2);

        Shortcut shortcut = new Shortcut("shortcut", file1);

        VirusScanner virusScanner = new VirusScanner();
        SizeAnalyzer sizeAnalyzer = new SizeAnalyzer();

        folder.accept(virusScanner); // проверка на вирусы
        folder.accept(sizeAnalyzer); // размер

        System.out.println("Общий размер файлов: " + sizeAnalyzer.getTotalSize() + " байт");
    }
}