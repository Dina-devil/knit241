package org.knit.solutions.task16;

public class MyCharacter {
    private char code;

    public MyCharacter(char code) {
        this.code = code;
    }

    public void render(int x, int y, String style) {
        System.out.println("Отображение символа " + code + " в позиции (" + x + "," + y + ") с стилем " + style);
    }
}
