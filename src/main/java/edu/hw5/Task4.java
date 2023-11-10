package edu.hw5;

public class Task4 {

    private Task4() {
    }

    public static boolean validatePass(String pass) {
        return pass.matches(".*[~!@#$%^&*|].*");
    }

}
