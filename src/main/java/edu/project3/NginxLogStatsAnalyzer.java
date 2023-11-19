package edu.project3;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class NginxLogStatsAnalyzer {

    public static void main(String[] args) {
        try {
            String path = args[0];
            String from = null;
            String to = null;
            String format = "markdown";

            for (int i = 0; i < args.length; i += 2) {
                String option = args[i];
                String value = args[i + 1];
                if ("--path".equals(option)) {
                    path = value;
                } else if ("--from".equals(option)) {
                    from = value;
                } else if ("--to".equals(option)) {
                    to = value;
                } else if ("--format".equals(option)) {
                    format = value;
                }
            }

            Stream<LogRecord> logRecords = readLogRecords(path);
            LogReport logReport = generateLogReport(logRecords, from, to);
            String output = formatLogReport(logReport, format);
            System.out.println(output);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid arguments. Please provide the necessary input.");
        }
    }

    private static Stream<LogRecord> readLogRecords(String path) {
        try {
            if (path.startsWith("http")) {
                return readLogRecordsFromUrl(path);
            } else {
                return readLogRecordsFromFiles(path);
            }
        } catch (IOException | URISyntaxException e) {
            System.err.println("Error reading log records: " + e.getMessage());
            return Stream.empty();
        }
    }

    private static Stream<LogRecord> readLogRecordsFromUrl(String url) throws IOException, URISyntaxException {
        URI uri = new URI(url);
        return Files.lines(Paths.get(uri))
            .map(line -> {
                String[] parts = line.split(" ");
                return new LogRecord(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5], parts[6]);
            });
    }

    private static Stream<LogRecord> readLogRecordsFromFiles(String filePathPattern) throws IOException {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + filePathPattern);
        Path currentDir = Paths.get(".").toAbsolutePath();
        System.out.println(currentDir);
        return Files.find(currentDir, 1, (path, attributes) -> pathMatcher.matches(path))
            .flatMap(path -> {
                try (Stream<String> lines = Files.lines(path)) {
                    return lines.map(line -> {
                        String[] parts = line.split(" ");
                        return new LogRecord(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5], parts[6]);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    return Stream.empty();
                }
            });
    }

    private static LogReport generateLogReport(Stream<LogRecord> logRecords, String from, String to) {
        ZoneOffset offset = OffsetDateTime.now().getOffset();
//        OffsetDateTime fromDate = OffsetDateTime.of(from, LocalTime.MIDNIGHT, offset);
//        OffsetDateTime toDate = OffsetDateTime.of(to, LocalTime.MIDNIGHT, offset);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        OffsetDateTime fromDate = from != null ? OffsetDateTime.parse(from, formatter) : null;
        OffsetDateTime toDate = to != null ? OffsetDateTime.parse(to, formatter) : null;

        LogReport logReport = new LogReport();
        logRecords
            .filter(record -> isWithinRange(record.getDateTime(), fromDate, toDate))
            .forEach(logReport::addLogRecord);

        return logReport;
    }

    private static boolean isWithinRange(OffsetDateTime dateTime, OffsetDateTime from, OffsetDateTime to) {
        if (from != null && dateTime.isBefore(from)) {
            return false;
        }
        return to == null || !dateTime.isAfter(to);
    }

    private static String formatLogReport(LogReport logReport, String format) {
        if ("adoc".equals(format)) {
            return logReport.toAsciiDoc();
        } else {
            return logReport.toMarkdown();
        }
    }
}

