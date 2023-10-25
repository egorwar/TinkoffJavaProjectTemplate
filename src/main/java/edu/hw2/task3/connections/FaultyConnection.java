package edu.hw2.task3.connections;

import edu.hw2.task3.ConnectionException;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {

    static final double SUCCESS_PROBABILITY = 0.3;

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) throws ConnectionException {
        LOGGER.info("Pending connection: " + this);

        boolean isConnected = new Random().nextDouble() <= SUCCESS_PROBABILITY;

        if (isConnected) {
            LOGGER.info("Executed command:\n" + command);
        } else {
            throw new ConnectionException();
        }

    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Connection " + this + " is closed");
    }
}
