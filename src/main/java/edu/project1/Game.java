package edu.project1;

import edu.project1.players.Player;
import edu.project1.states.GameState;
import edu.project1.states.GuessState;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class Game {

    private final Player player;

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
                    player.takeReply(GuessState.RIGHT.getMessage());
                    yield GameState.ACTIVE;
                }
            }

            case WRONG -> {
                if (currentMistake >= maxMistakes) {
                    yield GameState.LOSS;
                } else {
                    currentMistake++;
                    player.takeReply(GuessState.WRONG.getMessage());
                    yield GameState.ACTIVE;
                }
            }
            case INVALID -> {
                player.takeReply(GuessState.INVALID.getMessage());
                yield GameState.ACTIVE;
            }
            case REPEAT -> {
                player.takeReply(GuessState.REPEAT.getMessage());
                yield GameState.ACTIVE;
            }
        };
    }

    public Game(Dictionary wordDictionary, int maxMistakes, Player player) {

        if (maxMistakes < 0) {
            throw new IllegalArgumentException("The maximum number of mistakes should be positive or zero");
        }

        this.maxMistakes = maxMistakes;
        this.word = wordDictionary.getRandomWord();
        this.maskedWord = new char[word.length];
        Arrays.fill(maskedWord, '*');
        this.guessResolver = new GuessResolver(word);
        this.used = new HashSet<>();
        this.currentMistake = 0;
        this.state = GameState.ACTIVE;

        this.player = player;
    }

    @SuppressWarnings({"RegexpSinglelineJava", "ReturnCount"})
    public void run() throws IOException {

        player.takeReply(String.format(GameState.START.getMessage(), maxMistakes));
        player.takeReply(String.format(
            GameState.ACTIVE.getMessage(),
            new String(maskedWord),
            currentMistake,
            maxMistakes
        ));

        while (true) {

            playRound(guessResolver.resolve(player.getGuess(), maskedWord, used));

            if (state == GameState.GIVE_UP || state == GameState.LOSS) {
                player.takeReply(state.getMessage());
                return;
            } else if (state == GameState.WIN) {
                player.takeReply(String.format(state.getMessage(), new String(word)));
                return;
            } else if (state == GameState.ACTIVE) {
                player.takeReply(String.format(
                    state.getMessage(),
                    new String(maskedWord),
                    currentMistake,
                    maxMistakes
                ));
            }

        }

    }

}
