package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;

public class Task7 {

    /**
     * Performs binary rotation (circular shift) to the right.
     * <p>
     * A binary representation length for rotation is defined by the most significant bit,
     * not by variable type.
     * <p>
     * <i><b>Examples:</b>
     * <p>
     * rotateRight(33, 1) = rotateRight([100001], 1) = [110000] = 48
     * <p>
     * rotateRight(1, 3) = rotateRight([1], 3) = [1] = 1
     * </i>
     *
     * @param n     a positive number to rotate
     * @param shift a non-negative number of bits to shift
     * @return a number {@code n}, shifted circularly to the right by {@code shift} bits
     * @throws IllegalArgumentException if {@code n} is not positive or {@code shift} is negative
     */
    public static int rotateRight(int n, int shift) {

        if (n <= 0) {
            throw new IllegalArgumentException("Number to rotate should be positive");
        }

        if (shift < 0) {
            throw new IllegalArgumentException("The number of bits to shift cannot be negative");
        }

        if (n == 1 || shift == 0) {
            return n;
        }

        var binArray = numToBinArray(n);
        Collections.rotate(binArray, shift);

        return binArrayToNum(binArray);
    }

    /**
     * Performs binary rotation (circular shift) to the left.
     * <p>
     * A binary representation length for rotation is defined by the most significant bit,
     * not by variable type.
     * <p>
     * <i><b>Examples:</b>
     * <p>
     * rotateLeft(32, 1) = rotateLeft([100000], 1) = [000001] = 1
     * <p>
     * rotateLeft(1, 3) = rotateLeft([1], 3) = [1] = 1
     * </i>
     *
     * @param n     a positive number to rotate
     * @param shift a non-negative number of bits to shift
     * @return a number {@code n}, shifted circularly to the left by {@code shift} bits
     * @throws IllegalArgumentException if {@code n} is not positive or {@code shift} is negative
     */
    public static int rotateLeft(int n, int shift) {

        if (n <= 0) {
            throw new IllegalArgumentException("Number to rotate should be positive");
        }

        if (shift < 0) {
            throw new IllegalArgumentException("The number of bits to shift cannot be negative");
        }

        if (n == 1 || shift == 0) {
            return n;
        }

        var binArray = numToBinArray(n);
        Collections.rotate(binArray, -shift);

        return binArrayToNum(binArray);
    }

    private static ArrayList<Byte> numToBinArray(int num) {

        var binArray = new ArrayList<Byte>();

        while (num != 0) {
            binArray.addFirst((byte) (num % 2));
            num /= 2;
        }

        return binArray;
    }

    private static int binArrayToNum(ArrayList<Byte> binArray) {

        int num = 0;
        for (int multiplier = 1; !binArray.isEmpty(); multiplier *= 2) {
            num += multiplier * binArray.removeLast();
        }

        return num;
    }

}
