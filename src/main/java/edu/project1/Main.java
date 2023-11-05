package edu.project1;

import edu.project1.players.ConsolePlayer;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) throws IOException {
        var game = new Game(
            new Dictionary(new HashSet<>(Set.of("apple", "microsoft", "yandex"))),
            0,
            new ConsolePlayer()
        );
        game.run();

    }
}
