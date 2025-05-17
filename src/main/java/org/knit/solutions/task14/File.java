package org.knit.solutions.task14;

public class File implements FileSystemElement {
    private String name;
    private int size; // Размер файла

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}
