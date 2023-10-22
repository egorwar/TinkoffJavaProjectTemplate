package edu.project1;

import java.util.HashSet;
import java.util.Random;

public final class Dictionary {

    private final HashSet<String> dict;

    public Dictionary(HashSet<String> dict) {

        if (dict.isEmpty()) {
            throw new IllegalArgumentException("Word dictionary cannot be empty");
        } else if (dict.contains("")) {
            throw new IllegalArgumentException("Dictionary cannot have empty words");
        }

        this.dict = dict;

    }

    public char[] getRandomWord() {

        return dict.stream().skip(new Random().nextInt(dict.size())).findFirst().get().toCharArray();

    }

}
