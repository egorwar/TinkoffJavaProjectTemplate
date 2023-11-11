package edu.hw5;

public class Task6 {

    private Task6() {
    }

    public static boolean isSubstring(String sub, String src) {
        return src.matches(".*" + sub + ".*");
    }

}
