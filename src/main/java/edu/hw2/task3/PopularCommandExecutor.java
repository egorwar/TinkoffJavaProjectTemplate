package edu.hw2.task3;

import edu.hw2.task3.managers.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    private final static Logger LOGGER = LogManager.getLogger();

    private Throwable cause;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        var possibleException = tryExecute("apt update && apt upgrade -y");

        if (possibleException != null) {
            throw possibleException;
        } else {
            LOGGER.info("Packages have been updated!");
        }

    }

    ConnectionException tryExecute(String command) {

        var connection = manager.getConnection();

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {

            LOGGER.info(String.format("Attempt %d. Trying to connect ...\n", attempt));

            try (connection) {
                connection.execute(command);
            } catch (ConnectionException e) {
                LOGGER.info("Connection failed!");
                cause = e;
                continue;
            } catch (Exception e) {
                LOGGER.info("Other exception ...");
                continue;
            }

            return null;
        }

        var initialException = new ConnectionException();
        initialException.initCause(cause);

        return initialException;
    }
}
