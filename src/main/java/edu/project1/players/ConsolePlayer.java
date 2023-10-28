package edu.project1.players;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsolePlayer implements Player {

    BufferedReader reader;

    public ConsolePlayer() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String getGuess() throws IOException {
        return reader.readLine();
    }

    @SuppressWarnings("RegexpSinglelineJava")
    @Override
    public void takeReply(String reply) {
        System.out.println(reply + '\n');
    }
}
