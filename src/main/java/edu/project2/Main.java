package edu.project2;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    
    public static void main(String[] args) throws IOException {
        Maze maze = new Maze(13, 21);
        maze.solveMaze(1, 1, 11, 19);
        LOGGER.info('\n' + maze.printMaze());
    }
}
