package edu.hw1;

public class Task7 {

    /**
     * Performs binary rotation (circular shift) to the right.
     *
     * @param n a number to rotate
     * @param shift a number of bits to shift
     * @return a number {@code n}, shifted circularly to the right by {@code shift} bits
     * @throws IllegalArgumentException if parameters are not positive
     */
    public static int rotateRight(int n, int shift) {

        if (n <= 0 || shift <= 0) {
            throw new IllegalArgumentException("Params should be positive");
        }

        int maxSize = String.valueOf(n).length() - 2;

        return (n >>> shift) | (n << (maxSize - shift));
    }

    /**
     * Performs binary rotation (circular shift) to the left.
     *
     * @param n a number to rotate
     * @param shift a number of bits to shift
     * @return a number {@code n}, shifted circularly to the left by {@code shift} bits
     * @throws IllegalArgumentException if parameters are not positive
     */
    public static int rotateLeft(int n, int shift) {

        if (n <= 0 || shift <= 0) {
            throw new IllegalArgumentException("Params should be positive");
        }

        int maxSize = String.valueOf(n).length() - 2;

        return (n << shift) | (n >> (maxSize - shift));
    }

}
