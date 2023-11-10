package edu.hw5;

public class Task8 {

    private Task8() {
    }

    public static boolean unevenLength(String s) {
        return s.matches("^([01])([01]{2})*$");
    }

    public static boolean unevenFromZeroOrEvenFromOne(String s) {
        return s.matches("^(0|1[01])([01]{2})*$");
    }

    public static boolean zerosDivisibleByThree(String s) {
        return s.matches("^(1*01*01*01*)*$");
    }

    public static boolean exceptTwoOrThreeOnes(String s) {
        return s.matches("^(?!11$|111$)[01]*$");
    }

    public static boolean noConseqOnes(String s) {
        return s.matches("^(?!.*11)[01]*$");
    }

}
