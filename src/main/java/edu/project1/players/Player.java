package edu.project1.players;

import java.io.IOException;

public interface Player {

    /** reads data from the player into a String */
    String getGuess() throws IOException;

    /** gives a game response to the player */
    void takeReply(String reply);

}
