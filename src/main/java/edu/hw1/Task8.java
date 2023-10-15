package edu.hw1;

import java.util.HashSet;

public class Task8 {

    /**
     * Checks whether on a given chess board any knight cannot capture the others.
     *
     * @param board an 8-by-8 matrix, where 0 is an empty cell, 1 is the knight
     * @return <i>true</i> if none of the knights can capture the others, <i>false</i> otherwise
     * @throws IllegalArgumentException if the matrix is not 8-by-8 or any symbols except 0 and 1 are present
     */
    public static boolean knightBoardCapture(byte[][] board) {

        if (board.length != 8) {
            throw new IllegalArgumentException("A board should be an 8x8 array");
        }

        var knightPositions = getKnightPositions(board);

        var possibleMoves = new byte[][] {
            {1, 2}, {2, 1},
            {-1, 2}, {2, -1},
            {1, -2}, {-2, 1},
            {-1, -2}, {-2, -1}
        };

        for (var position : knightPositions) {

            byte curKnightX = position[1];
            byte curKnightY = position[0];

            for (var move : possibleMoves) {

                byte movedKnightX = (byte) (curKnightX + move[1]);
                byte movedKnightY = (byte) (curKnightY + move[0]);

                if (movedKnightX < 0 || movedKnightX > 7 || movedKnightY < 0 || movedKnightY > 7) {

                    continue;

                } else if (board[movedKnightY][movedKnightX] == 1) {

                    return false;

                }

            }

        }

        return true;

    }

    private static HashSet<Byte[]> getKnightPositions(byte[][] board) {

        var knightPositions = new HashSet<Byte[]>();

        for (byte i = 0; i < 8; i++) {

            if (board[i].length != 8) {
                throw new IllegalArgumentException("A board should be an 8x8 array");
            }
            for (byte j = 0; j < 8; j++) {

                if (board[i][j] == 1) {
                    knightPositions.add(new Byte[] {i, j});
                } else if (board[i][j] != 0) {
                    throw new IllegalArgumentException("A board can only contain numbers of 0 and 1");
                }

            }

        }

        return knightPositions;
    }

}
