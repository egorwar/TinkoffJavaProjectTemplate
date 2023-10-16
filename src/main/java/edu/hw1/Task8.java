package edu.hw1;

import java.util.HashSet;

public class Task8 {

    static final byte BOARD_SIDE = 8;
    static final byte EMPTY_CELL = 0;
    static final byte KNIGHT = 1;

    static final byte[][] KNIGHT_MOVES = new byte[][] {
        {1, 2},
        {2, 1},
        {-1, 2},
        {2, -1},
        {1, -2},
        {-2, 1},
        {-1, -2},
        {-2, -1}
    };

    private Task8() {
    }

    /**
     * Checks whether on a given chess board any knight cannot capture the others.
     *
     * @param board an 8-by-8 matrix, where 0 is an empty cell, 1 is the knight
     * @return <i>true</i> if none of the knights can capture the others, <i>false</i> otherwise
     * @throws IllegalArgumentException if the matrix is not 8-by-8 or any symbols except 0 and 1 are present
     */
    @SuppressWarnings("MultipleStringLiterals")
    public static boolean knightBoardCapture(byte[][] board) {

        if (board.length != BOARD_SIDE) {
            throw new IllegalArgumentException("A board should be an 8x8 array");
        }

        var knightPositions = getKnightPositions(board);

        for (var position : knightPositions) {

            byte curKnightX = position[1];
            byte curKnightY = position[0];

            for (var move : KNIGHT_MOVES) {

                byte movedKnightX = (byte) (curKnightX + move[1]);
                byte movedKnightY = (byte) (curKnightY + move[0]);

                if (movedKnightX < 0 || movedKnightX >= BOARD_SIDE
                    || movedKnightY < 0 || movedKnightY >= BOARD_SIDE) {

                    continue;

                } else if (board[movedKnightY][movedKnightX] == KNIGHT) {

                    return false;

                }

            }

        }

        return true;

    }

    @SuppressWarnings("MultipleStringLiterals")
    private static HashSet<Byte[]> getKnightPositions(byte[][] board) {

        var knightPositions = new HashSet<Byte[]>();

        for (byte i = 0; i < BOARD_SIDE; i++) {

            if (board[i].length != BOARD_SIDE) {
                throw new IllegalArgumentException("A board should be an 8x8 array");
            }
            for (byte j = 0; j < BOARD_SIDE; j++) {

                if (board[i][j] == KNIGHT) {
                    knightPositions.add(new Byte[] {i, j});
                } else if (board[i][j] != EMPTY_CELL) {
                    throw new IllegalArgumentException("A board can only contain numbers of 0 and 1");
                }

            }

        }

        return knightPositions;
    }

}
