package edu.hw2.task3.managers;

import edu.hw2.task3.connections.Connection;
import edu.hw2.task3.connections.FaultyConnection;
import edu.hw2.task3.connections.StableConnection;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultConnectionManager implements ConnectionManager {

    private final static Logger LOGGER = LogManager.getLogger();

    static final double FAULTY_CONNECTION_PROBABILITY = 0.7;

    @Override
    public Connection getConnection() {

        boolean isFaultyConnection = new Random().nextDouble() <= FAULTY_CONNECTION_PROBABILITY;

        if (isFaultyConnection) {
            LOGGER.info("Faulty connection found");
            return new FaultyConnection();
        } else {
            LOGGER.info("Stable connection found");
            return new StableConnection();
        }

    }
}
