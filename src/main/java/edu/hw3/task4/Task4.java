package edu.hw3.task4;

import java.util.List;

public class Task4 {

    private static final int MAX_ROMAN_NUM = 4000;

    private Task4() {
    }

    @SuppressWarnings("ParameterAssignment")
    public static String arabicToRoman(int number) {

        if (number <= 0 || number > MAX_ROMAN_NUM) {
            throw new IllegalArgumentException("a number should be in range (0,4000]");
        }

        List<RomanNumber> romanNumbers = RomanNumber.getReverseSortedValues();

        int i = 0;
        StringBuilder roman = new StringBuilder();

        while (number > 0 && i < romanNumbers.size()) {

            RomanNumber cur = romanNumbers.get(i);
            if (cur.getValue() <= number) {

                roman.append(cur.name());
                number -= cur.getValue();

            } else {
                i++;
            }

        }

        return roman.toString();
    }

}
