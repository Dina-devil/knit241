package org.knit.solutions.task16;

import java.util.HashMap;
import java.util.Map;

public class CharacterFactory {
    private Map<Character, MyCharacter> characters = new HashMap<>();

    public MyCharacter getCharacter(char code) {
        MyCharacter character = characters.get(code);
        if (character == null) {
            character = new MyCharacter(code);
            characters.put(code, character);
        }
        return character;
    }
}



