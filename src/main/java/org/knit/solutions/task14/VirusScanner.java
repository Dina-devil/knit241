package org.knit.solutions.task14;

public class VirusScanner implements FileSystemVisitor {

    @Override
    public void visit(File file) {
        System.out.println("Проверка файла " + file.getName() + " на вирусы.");
    }

    @Override
    public void visit(Folder folder) {
        System.out.println("Проверка папки " + folder.name + " на вирусы.");
        for (FileSystemElement element : folder.getElements()) {
            element.accept(this);
        }
    }

    @Override
    public void visit(Shortcut shortcut) {
        System.out.println("Проверка ярлыка " + shortcut.name + " на вирусы.");
        shortcut.getTarget().accept(this);
    }
}