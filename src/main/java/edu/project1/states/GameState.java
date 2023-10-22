package edu.project1.states;

public enum GameState {
    START("""
        Welcome to the Hangman!
        Your goal is to guess the word one symbol at a time.
        If your guess was correct, every such symbol would be revealed.
        If you can guess the world with no more than %d mistakes, you win!
        You can always type "yield" to leave the game.
        Good luck!"""),

    ACTIVE("""
        The word is: %s
        %d mistakes out of %d
        Guess a symbol:\s"""),

    WIN("""
        The word is: %s
        YOU WON!
        Congratulations!"""),

    LOSS("you lost ...\n"
        + "Better luck next time!"),

    GIVE_UP("You've left the game");

    private final String message;

    GameState(String s) {
        this.message = s;
    }

    public String getMessage() {
        return this.message;
    }
}
