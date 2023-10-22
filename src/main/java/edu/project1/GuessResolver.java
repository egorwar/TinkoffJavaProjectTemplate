package edu.project1;

import edu.project1.states.GuessState;
import java.util.HashSet;
import java.util.stream.IntStream;

public class GuessResolver {

    private final char[] word;

    public GuessResolver(char[] word) {

        if (word.length == 0) {
            throw new IllegalArgumentException(
                "A guessed word cannot be empty.\nBetter use Dictionary object's getRandomWord() method");
        }

        this.word = word;
    }

    @SuppressWarnings("ReturnCount")
    public GuessState resolve(String guess, char[] maskedWord, HashSet<Character> used) {

        if (guess.equalsIgnoreCase("yield")) {
            return GuessState.GIVE_UP;
        } else if (guess.length() != 1) {
            return GuessState.INVALID;
        } else if (used.contains(guess.charAt(0))) {
            return GuessState.REPEAT;
        }

        var guessedIndices = IntStream.range(0, word.length)
            .filter(i -> word[i] == guess.charAt(0))
            .boxed().toList();

        if (guessedIndices.isEmpty()) {

            used.add(guess.charAt(0));
            return GuessState.WRONG;

        } else {

            for (int guessedIndex : guessedIndices) {
                maskedWord[guessedIndex] = word[guessedIndex];
            }

            used.add(guess.charAt(0));
            return GuessState.RIGHT;

        }
    }

}
