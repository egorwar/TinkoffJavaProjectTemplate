package edu.project1.states;

public enum GuessState {
    RIGHT("GOOD GUESS!\n"),
    WRONG("WRONG!\n"),
    INVALID("You should give a single letter\n"),
    REPEAT("This letter has already been given!\n"),
    GIVE_UP("You've left the game");

    private final String message;

    GuessState(String s) {
        this.message = s;
    }

    public String getMessage() {
        return this.message;
    }
}
