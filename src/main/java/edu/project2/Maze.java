package edu.project2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

@SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {

        if (height <= 2 || width <= 2 || height % 2 == 0 || width % 2 == 0) {
            throw new IllegalArgumentException("Height and width must be odd numbers greater than 2");
        }

        this.height = height;
        this.width = width;

        this.grid = build(new Random());

    }

    public Maze(int height, int width, long seed) {

        if (height <= 2 || width <= 2 || height % 2 == 0 || width % 2 == 0) {
            throw new IllegalArgumentException("Height and width must be odd numbers greater than 2");
        }

        this.height = height;
        this.width = width;

        this.grid = build(new Random(seed));

    }

    private Cell[][] build(Random random) {
        Cell[][] maze = new Cell[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                maze[row][col] = new Cell(row, col, Cell.Type.WALL, false, null);
            }
        }

        recursiveBacktracking(random, maze, 1, 1);

        return maze;
    }

    private void recursiveBacktracking(Random random, Cell[][] maze, int row, int col) {

        maze[row][col].setType(Cell.Type.EMPTY);
        int[] directions = {0, 1, 2, 3};

        for (int i = 0; i < directions.length; i++) {
            int randomIndex = random.nextInt(directions.length);
            int temp = directions[i];
            directions[i] = directions[randomIndex];
            directions[randomIndex] = temp;
        }

        for (int direction : directions) {
            int newRow = row + getRowOffset(direction);
            int newCol = col + getColOffset(direction);

            if (isValidNeighbor(maze, newRow, newCol)) {
                maze[row + getRowOffset(direction) / 2][col + getColOffset(direction) / 2].setType(Cell.Type.EMPTY);
                recursiveBacktracking(random, maze, newRow, newCol);
            }
        }
    }

    private boolean isValidNeighbor(Cell[][] maze, int row, int col) {
        return row > 0 && row < height && col > 0 && col < width && maze[row][col].getType() == Cell.Type.WALL;
    }

    private int getRowOffset(int direction) {
        return switch (direction) {
            case 0 -> -2;
            case 1 -> 2;
            case 2 -> 0;
            case 3 -> 0;
            default -> 0;
        };
    }

    private int getColOffset(int direction) {
        return switch (direction) {
            case 0 -> 0;
            case 1 -> 0;
            case 2 -> -2;
            case 3 -> 2;
            default -> 0;
        };
    }

    public void solveMaze(int startX, int startY, int endX, int endY) {

        validateCell(startX, startY);
        validateCell(endX, endY);

        Deque<Cell> stack = new ArrayDeque<>();
        Cell startCell = grid[startX][startY];
        Cell endCell = grid[endX][endY];

        stack.push(startCell);
        startCell.setVisited(true);

        while (!stack.isEmpty()) {
            Cell currentCell = stack.peek();

            if (currentCell == endCell) {
                break;
            }

            boolean foundValidNeighbor = false;
            for (int direction = 0; direction < 4; direction++) {
                int newRow = currentCell.getRow() + getRowOffset2(direction);
                int newCol = currentCell.getCol() + getColOffset2(direction);

                if (isValidNeighbor(newRow, newCol)) {
                    foundValidNeighbor = true;

                    Cell neighborCell = grid[newRow][newCol];
                    neighborCell.setVisited(true);
                    neighborCell.setPrevious(currentCell);
                    stack.push(neighborCell);

                    break;
                }
            }

            if (!foundValidNeighbor) {
                stack.pop();
            }
        }

        markPath(endCell);
    }

    private void markPath(Cell endCell) {
        Cell currentCell = endCell;
        while (currentCell != null) {
            currentCell.setType(Cell.Type.PATH);
            currentCell = currentCell.getPrevious();
        }
    }

    private int getRowOffset2(int direction) {
        return switch (direction) {
            case 0 -> -1;
            case 1 -> 1;
            case 2 -> 0;
            case 3 -> 0;
            default -> 0;
        };
    }

    private int getColOffset2(int direction) {
        return switch (direction) {
            case 0 -> 0;
            case 1 -> 0;
            case 2 -> -1;
            case 3 -> 1;
            default -> 0;
        };
    }

    private boolean isValidNeighbor(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width && !grid[row][col].isVisited()
            && grid[row][col].getType() != Cell.Type.WALL;
    }

    private void validateCell(int x, int y) {
        if (x < 0 || x >= height || y < 0 || y >= width || grid[x][y].getType() == Cell.Type.WALL) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
    }

    public String printMaze() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                sb.append(grid[row][col].getType().getSymbol());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
