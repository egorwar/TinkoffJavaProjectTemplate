package edu.project2;

public class Cell {
    private final int row;
    private final int col;
    private Cell.Type type;
    private boolean isVisited;
    private Cell previous;

    public Cell(int row, int col, Cell.Type type, boolean isVisited, Cell previous) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.isVisited = isVisited;
        this.previous = previous;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Cell.Type getType() {
        return type;
    }

    public void setType(Cell.Type type) {
        this.type = type;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        this.isVisited = visited;
    }

    public Cell getPrevious() {
        return previous;
    }

    public void setPrevious(Cell previous) {
        this.previous = previous;
    }

    public enum Type {
        WALL("█"),
        EMPTY(" "),
        PATH("*");

        private final String symbol;

        Type(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }

}
