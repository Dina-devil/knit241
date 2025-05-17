package org.knit.solutions.task14;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemElement {
    String name;
    private List<FileSystemElement> elements;

    public Folder(String name) {
        this.name = name;
        this.elements = new ArrayList<>();
    }

    public void addElement(FileSystemElement element) {
        elements.add(element);
    }

    public List<FileSystemElement> getElements() {
        return elements;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}
