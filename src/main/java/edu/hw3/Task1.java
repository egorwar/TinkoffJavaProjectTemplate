package edu.hw3;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task1 {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private static HashMap<Character, Character> inverse;

    private Task1() {
    }

    private static void buildInverse() {

        char[] lowerAlph = ALPHABET.toCharArray();
        char[] upperAlph = ALPHABET.toUpperCase().toCharArray();

        Map<Character, Character> lowerInverse = IntStream.range(0, ALPHABET.length()).boxed().collect(
            Collectors.toMap(i -> lowerAlph[i], i -> lowerAlph[ALPHABET.length() - i - 1]));
        Map<Character, Character> upperInverse = IntStream.range(0, ALPHABET.length()).boxed().collect(
            Collectors.toMap(i -> upperAlph[i], i -> upperAlph[ALPHABET.length() - i - 1]));

        inverse = new HashMap<Character, Character>();
        inverse.putAll(lowerInverse);
        inverse.putAll(upperInverse);

    }

    public static String atbash(String string) {

        if (inverse == null) {
            buildInverse();
        }

        char[] charArray = string.toCharArray();

        for (int i = 0; i < charArray.length; i++) {

            var substitution = inverse.get(charArray[i]);

            if (substitution != null) {
                charArray[i] = substitution;
            }

        }

        return new String(charArray);

    }

}
