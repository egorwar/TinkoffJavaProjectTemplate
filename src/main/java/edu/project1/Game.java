package edu.project1;

import edu.project1.states.GameState;
import edu.project1.states.GuessState;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Game {
    private BufferedReader r;
    private final int maxMistakes;
    private final char[] word;
    private final char[] maskedWord;

    private final HashSet<Character> used;

    private final GuessResolver guessResolver;

    private GameState state;

    private int currentMistake;

    @SuppressWarnings("RegexpSinglelineJava")
    private void playRound(GuessState state) {
        this.state = switch (state) {

            case GIVE_UP -> GameState.GIVE_UP;

            case RIGHT -> {
                if (Arrays.equals(maskedWord, word)) {
                    yield GameState.WIN;
                } else {
                    System.out.println(GuessState.RIGHT.getMessage());
                    yield GameState.ACTIVE;
                }
            }

            case WRONG -> {
                if (currentMistake >= maxMistakes) {
                    yield GameState.LOSS;
                } else {
                    currentMistake++;
                    System.out.println(GuessState.WRONG.getMessage());
                    yield GameState.ACTIVE;
                }
            }
            case INVALID -> {
                System.out.println(GuessState.INVALID.getMessage());
                yield GameState.ACTIVE;
            }
            case REPEAT -> {
                System.out.println(GuessState.REPEAT.getMessage());
                yield GameState.ACTIVE;
            }
        };
    }

    public Game(Dictionary wordDictionary, int maxMistakes) {

        if (maxMistakes < 0) {
            throw new IllegalArgumentException("The maximum number of mistakes should be positive or zero");
        }

        this.r = new BufferedReader(new InputStreamReader(System.in));

        this.maxMistakes = maxMistakes;
        this.word = wordDictionary.getRandomWord();
        this.maskedWord = new char[word.length];
        Arrays.fill(maskedWord, '*');
        this.guessResolver = new GuessResolver(word);
        this.used = new HashSet<>();
        this.currentMistake = 0;
        this.state = GameState.ACTIVE;
    }

    @SuppressWarnings({"RegexpSinglelineJava", "ReturnCount"})
    public void run() throws IOException {

        System.out.println(GameState.START.getMessage());
        System.out.printf(GameState.ACTIVE.getMessage(), new String(maskedWord), currentMistake, maxMistakes);

        while (true) {

            playRound(guessResolver.resolve(r.readLine(), maskedWord, used));

            if (state == GameState.GIVE_UP || state == GameState.LOSS) {
                System.out.println(state.getMessage());
                return;
            } else if (state == GameState.WIN) {
                System.out.printf(state.getMessage(), new String(word));
                return;
            } else if (state == GameState.ACTIVE) {
                System.out.printf(state.getMessage(), new String(maskedWord), currentMistake, maxMistakes);
            }

        }

    }

}
