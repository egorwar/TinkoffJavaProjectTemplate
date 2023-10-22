package edu.hw2.task3.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        LOGGER.info("Pending connection: " + this);
        LOGGER.info("Executed command:\n" + command);
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Connection " + this + " is closed");
    }
}
