package edu.project3;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) throws IOException {
        String[] arguments =
            ("--path /Users/hoba/Downloads/JavaTinkoff/TinkoffJavaProjectTemplate/logs.txt"
            + "--from 2023-11-17 "
            + "--to 2023-11-18"
            + "--format adoc").split(" ");
        LOGGER.info('\n' + NginxLogStatsAnalyzer.execute(args));
    }
}
