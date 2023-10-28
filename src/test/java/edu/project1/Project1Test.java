package edu.project1;

import edu.project1.players.ConsolePlayer;
import edu.project1.players.MockPlayer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Project1Test {
    @Test
    @DisplayName("Проверка на задание пустого словаря")
    void emptyDict() {
        // given

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Dictionary(new HashSet<>()),
            "Expected Dictionary constructor with empty set to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Word dictionary cannot be empty");
    }

    @Test
    @DisplayName("Проверка на задание словаря с некорректными вхождениями")
    void incorrectDict() {
        // given

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Dictionary(new HashSet<>(Set.of("good_word", ""))),
            "Expected Dictionary constructor with bad set to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Dictionary cannot have empty words");
    }

    @Test
    @DisplayName("Передача пустого слова в GuessResolver")
    void incorrectGuessResolverEntry() {
        // given

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new GuessResolver("".toCharArray()),
            "Expected GuessResolver constructor with bad char array to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo(
            "A guessed word cannot be empty.\nBetter use Dictionary object's getRandomWord() method");
    }

    @Test
    @DisplayName("Попытка задать игру с отрицательным числом ошибок")
    void negativeNumberOfMistakesGame() {
        // given

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Game(new Dictionary(new HashSet<>(Set.of("test"))), -1, new ConsolePlayer()),
            "Expected Game constructor with negative number of mistakes to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo(
            "The maximum number of mistakes should be positive or zero");
    }

    @Test
    @DisplayName("Проверка на угадывание")
    void goodAnswer() throws IOException {
        // given
        var player = new MockPlayer();
        Game game = new Game(new Dictionary(new HashSet<>(Set.of("apple"))), 1, player);
        var guessList = new LinkedList<String>();
        guessList.offer("p");
        guessList.offer("yield");

        var expected = new LinkedList<String>();
        expected.offer("""
            Welcome to the Hangman!
            Your goal is to guess the word one symbol at a time.
            If your guess was correct, every such symbol would be revealed.
            If you can guess the world with no more than 1 mistakes, you win!
            You can always type "yield" to leave the game.
            Good luck!""");
        expected.offer("""
            The word is: *****
            0 mistakes out of 1
            Guess a symbol:\s""");

        expected.offer("""
            GOOD GUESS!
            """);
        expected.offer("""
            The word is: *pp**
            0 mistakes out of 1
            Guess a symbol:\s""");
        expected.offer("You've left the game");

        var response = new LinkedList<String>();

        // when
        player.putMockGuesses(guessList);
        game.run();
        response = player.getMockOutput();

        // then
        assertThat(response).isEqualTo(expected);
    }

    @Test
    @DisplayName("Проверка на неугадывание")
    void badAnswer() throws IOException {
        // given
        var player = new MockPlayer();
        Game game = new Game(new Dictionary(new HashSet<>(Set.of("apple"))), 1, player);
        var guessList = new LinkedList<String>();
        guessList.offer("q");
        guessList.offer("yield");

        var expected = new LinkedList<String>();
        expected.offer("""
            Welcome to the Hangman!
            Your goal is to guess the word one symbol at a time.
            If your guess was correct, every such symbol would be revealed.
            If you can guess the world with no more than 1 mistakes, you win!
            You can always type "yield" to leave the game.
            Good luck!""");
        expected.offer("""
            The word is: *****
            0 mistakes out of 1
            Guess a symbol:\s""");

        expected.offer("""
            WRONG!
            """);
        expected.offer("""
            The word is: *****
            1 mistakes out of 1
            Guess a symbol:\s""");
        expected.offer("You've left the game");

        var response = new LinkedList<String>();

        // when
        player.putMockGuesses(guessList);
        game.run();
        response = player.getMockOutput();

        // then
        assertThat(response).isEqualTo(expected);
    }

    @Test
    @DisplayName("Проверка на дубликат")
    void repeatedAnswer() throws IOException {
        // given
        var player = new MockPlayer();
        Game game = new Game(new Dictionary(new HashSet<>(Set.of("apple"))), 1, player);
        var guessList = new LinkedList<String>();
        guessList.offer("p");
        guessList.offer("p");
        guessList.offer("yield");

        var expected = new LinkedList<String>();
        expected.offer("""
            Welcome to the Hangman!
            Your goal is to guess the word one symbol at a time.
            If your guess was correct, every such symbol would be revealed.
            If you can guess the world with no more than 1 mistakes, you win!
            You can always type "yield" to leave the game.
            Good luck!""");
        expected.offer("""
            The word is: *****
            0 mistakes out of 1
            Guess a symbol:\s""");

        expected.offer("""
            GOOD GUESS!
            """);
        expected.offer("""
            The word is: *pp**
            0 mistakes out of 1
            Guess a symbol:\s""");
        expected.offer("""
            This letter has already been given!
            """);
        expected.offer("""
            The word is: *pp**
            0 mistakes out of 1
            Guess a symbol:\s""");
        expected.offer("You've left the game");

        var response = new LinkedList<String>();

        // when
        player.putMockGuesses(guessList);
        game.run();
        response = player.getMockOutput();

        // then
        assertThat(response).isEqualTo(expected);
    }

    @Test
    @DisplayName("Проверка на некорректную строку")
    void incorrectAnswer() throws IOException {
        // given
        var player = new MockPlayer();
        Game game = new Game(new Dictionary(new HashSet<>(Set.of("apple"))), 1, player);
        var guessList = new LinkedList<String>();
        guessList.offer("p");
        guessList.offer("lol");
        guessList.offer("yield");

        var expected = new LinkedList<String>();
        expected.offer("""
            Welcome to the Hangman!
            Your goal is to guess the word one symbol at a time.
            If your guess was correct, every such symbol would be revealed.
            If you can guess the world with no more than 1 mistakes, you win!
            You can always type "yield" to leave the game.
            Good luck!""");
        expected.offer("""
            The word is: *****
            0 mistakes out of 1
            Guess a symbol:\s""");

        expected.offer("""
            GOOD GUESS!
            """);
        expected.offer("""
            The word is: *pp**
            0 mistakes out of 1
            Guess a symbol:\s""");
        expected.offer("""
            You should give a single letter
            """);
        expected.offer("""
            The word is: *pp**
            0 mistakes out of 1
            Guess a symbol:\s""");
        expected.offer("You've left the game");

        var response = new LinkedList<String>();

        // when
        player.putMockGuesses(guessList);
        game.run();
        response = player.getMockOutput();

        // then
        assertThat(response).isEqualTo(expected);
    }

    @Test
    @DisplayName("Проверка на выигрыш")
    void win() throws IOException {
        // given
        var player = new MockPlayer();
        Game game = new Game(new Dictionary(new HashSet<>(Set.of("apple"))), 1, player);
        var guessList = new LinkedList<String>();
        guessList.offer("p");
        guessList.offer("a");
        guessList.offer("q");
        guessList.offer("l");
        guessList.offer("e");

        var expected = new LinkedList<String>();
        expected.offer("""
            Welcome to the Hangman!
            Your goal is to guess the word one symbol at a time.
            If your guess was correct, every such symbol would be revealed.
            If you can guess the world with no more than 1 mistakes, you win!
            You can always type "yield" to leave the game.
            Good luck!""");
        expected.offer("""
            The word is: *****
            0 mistakes out of 1
            Guess a symbol:\s""");

        expected.offer("""
            GOOD GUESS!
            """);
        expected.offer("""
            The word is: *pp**
            0 mistakes out of 1
            Guess a symbol:\s""");
        expected.offer("""
            GOOD GUESS!
            """);
        expected.offer("""
            The word is: app**
            0 mistakes out of 1
            Guess a symbol:\s""");
        expected.offer("""
            WRONG!
            """);
        expected.offer("""
            The word is: app**
            1 mistakes out of 1
            Guess a symbol:\s""");
        expected.offer("""
            GOOD GUESS!
            """);
        expected.offer("""
            The word is: appl*
            1 mistakes out of 1
            Guess a symbol:\s""");
        expected.offer("""
            The word is: apple
            YOU WON!
            Congratulations!""");

        var response = new LinkedList<String>();

        // when
        player.putMockGuesses(guessList);
        game.run();
        response = player.getMockOutput();

        // then
        assertThat(response).isEqualTo(expected);
    }

    @Test
    @DisplayName("Проверка на проигрыш")
    void loss() throws IOException {
        // given
        var player = new MockPlayer();
        Game game = new Game(new Dictionary(new HashSet<>(Set.of("apple"))), 1, player);
        var guessList = new LinkedList<String>();
        guessList.offer("p");
        guessList.offer("a");
        guessList.offer("q");
        guessList.offer("w");

        var expected = new LinkedList<String>();
        expected.offer("""
            Welcome to the Hangman!
            Your goal is to guess the word one symbol at a time.
            If your guess was correct, every such symbol would be revealed.
            If you can guess the world with no more than 1 mistakes, you win!
            You can always type "yield" to leave the game.
            Good luck!""");
        expected.offer("""
            The word is: *****
            0 mistakes out of 1
            Guess a symbol:\s""");

        expected.offer("""
            GOOD GUESS!
            """);
        expected.offer("""
            The word is: *pp**
            0 mistakes out of 1
            Guess a symbol:\s""");
        expected.offer("""
            GOOD GUESS!
            """);
        expected.offer("""
            The word is: app**
            0 mistakes out of 1
            Guess a symbol:\s""");
        expected.offer("""
            WRONG!
            """);
        expected.offer("""
            The word is: app**
            1 mistakes out of 1
            Guess a symbol:\s""");
        expected.offer("you lost ...\n"
            + "Better luck next time!");

        var response = new LinkedList<String>();

        // when
        player.putMockGuesses(guessList);
        game.run();
        response = player.getMockOutput();

        // then
        assertThat(response).isEqualTo(expected);
    }

}
