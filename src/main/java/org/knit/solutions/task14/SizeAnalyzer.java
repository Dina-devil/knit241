package org.knit.solutions.task14;

public class SizeAnalyzer implements FileSystemVisitor {

    private int totalSize = 0;

    @Override
    public void visit(File file) {
        totalSize += file.getSize();
    }

    @Override
    public void visit(Folder folder) {
        for (FileSystemElement element : folder.getElements()) {
            element.accept(this);
        }
    }

    @Override
    public void visit(Shortcut shortcut) {
    }

    public int getTotalSize() {
        return totalSize;
    }
}
