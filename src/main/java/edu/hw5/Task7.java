package edu.hw5;

public class Task7 {

    private Task7() {
    }

    public static boolean threeOrMoreThirdIsZero(String s) {
        return s.matches("^[01]{2}0[01]*");
    }

    public static boolean startEqualsEnd(String s) {
        return s.matches("^([01])([01]*\\1)?$");
    }

    public static boolean oneToThree(String s) {
        return s.matches("^[01]{1,3}$");
    }

}
