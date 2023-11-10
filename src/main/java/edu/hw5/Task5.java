package edu.hw5;

public class Task5 {

    private Task5() {
    }

    public static boolean validateRusRegPlate(String plate) {
        return plate.matches("^[А-Я]\\d{3}[А-Я]{2}\\d{3}$");
    }

}
