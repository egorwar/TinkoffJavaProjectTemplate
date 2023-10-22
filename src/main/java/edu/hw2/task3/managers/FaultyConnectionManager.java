package edu.hw2.task3.managers;

import edu.hw2.task3.connections.Connection;
import edu.hw2.task3.connections.FaultyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnectionManager implements ConnectionManager {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public Connection getConnection() {

        LOGGER.info("Faulty connection found");
        return new FaultyConnection();

    }
}
