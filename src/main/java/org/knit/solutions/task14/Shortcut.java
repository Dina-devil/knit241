package org.knit.solutions.task14;

public class Shortcut implements FileSystemElement {
    String name;
    private FileSystemElement target;

    public Shortcut(String name, FileSystemElement target) {
        this.name = name;
        this.target = target;
    }

    public FileSystemElement getTarget() {
        return target;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}
