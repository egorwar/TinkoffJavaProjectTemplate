package edu.project2;

public class Cell {
    private int row;
    private int col;
    private Cell.Type type;
    private boolean visited;
    private Cell previous;

    public Cell(int row, int col, Cell.Type type, boolean visited, Cell previous) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.visited = visited;
        this.previous = previous;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Cell.Type getType() {
        return type;
    }

    public void setType(Cell.Type type) {
        this.type = type;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Cell getPrevious() {
        return previous;
    }

    public void setPrevious(Cell previous) {
        this.previous = previous;
    }

    public enum Type {
        WALL("\u2588"),
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
