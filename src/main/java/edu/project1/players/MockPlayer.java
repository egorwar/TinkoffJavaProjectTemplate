package edu.project1.players;

import java.util.LinkedList;

public class MockPlayer implements Player {

    private LinkedList<String> mockPlayerGuesses;
    private final LinkedList<String> mockOutput;

    public MockPlayer() {
        mockOutput = new LinkedList<>();
    }

    @Override
    public String getGuess() {
        return mockPlayerGuesses.poll();
    }

    @Override
    public void takeReply(String reply) {
        mockOutput.offer(reply);
    }

    public void putMockGuesses(LinkedList<String> mockPlayerGuesses) {
        this.mockPlayerGuesses = mockPlayerGuesses;
    }

    public LinkedList<String> getMockOutput() {
        return mockOutput;
    }

}
