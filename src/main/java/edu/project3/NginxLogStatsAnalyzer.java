package edu.project3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.stream.Stream;

@SuppressWarnings({"MultipleStringLiterals", "MagicNumber"})
public class NginxLogStatsAnalyzer {

    private NginxLogStatsAnalyzer() {
    }

    public static String execute(String[] args) {

        String path = null;
        String from = null;
        String to = null;
        String format = null;
        try {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Arguments should come in pairs like: [--arg] [value]");
        }

        if (path == null) {
            throw new IllegalArgumentException("--path is a mandatory argument");
        }

        Stream<LogRecord> logRecords = readLogRecords(path);
        LogReport logReport = generateLogReport(logRecords, from, to, path);
        String output = formatLogReport(logReport, format);

        if (format != null) {
            String name = "report." + ((Objects.equals(format, "adoc")) ? "adoc" : "md");
            try (PrintWriter out = new PrintWriter(name)) {
                out.println(output);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return output;
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
                String[] parts = line.split("\"");
                String ipAddress = parts[0].split(" ")[0];
                String datetime = parts[0].substring(parts[0].indexOf('[') + 1, parts[0].indexOf(']'));
                String request = parts[1];
                int status = Integer.parseInt(parts[2].trim().split(" ")[0]);
                int bytesSent = Integer.parseInt(parts[2].trim().split(" ")[1]);
                String referer = parts[3].trim();
                String userAgent = parts[5].trim();
                return new LogRecord(ipAddress, datetime, request, status, bytesSent, referer, userAgent);

            });
    }

    private static Stream<LogRecord> readLogRecordsFromFiles(String filePathPattern) throws IOException {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + filePathPattern);
        Path currentDir = Paths.get("").toAbsolutePath();
        return Files.find(currentDir, 1, (path, attributes) -> pathMatcher.matches(path))
            .flatMap(path -> {
                try {
                    return Files.lines(path).map(line -> {
                        String[] parts = line.split("\"");
                        String ipAddress = parts[0].split(" ")[0];
                        String datetime = parts[0].substring(parts[0].indexOf('[') + 1, parts[0].indexOf(']'));
                        String request = parts[1];
                        int status = Integer.parseInt(parts[2].trim().split(" ")[0]);
                        int bytesSent = Integer.parseInt(parts[2].trim().split(" ")[1]);
                        String referer = parts[3].trim();
                        String userAgent = parts[5].trim();
                        return new LogRecord(ipAddress, datetime, request, status, bytesSent, referer, userAgent);

                    });
                } catch (IOException e) {
                    return Stream.empty();
                }
            });
    }

    private static LogReport generateLogReport(Stream<LogRecord> logRecords, String from, String to, String path) {
        ZoneOffset offset = OffsetDateTime.now().getOffset();
        OffsetDateTime fromDate =
            from != null ? OffsetDateTime.of(LocalDate.parse(from), LocalTime.MIDNIGHT, offset) : null;
        OffsetDateTime toDate =
            from != null ? OffsetDateTime.of(LocalDate.parse(to), LocalTime.MIDNIGHT, offset) : null;

        LogReport logReport = new LogReport(path, fromDate, toDate);
        logRecords
            .filter(logRecord -> isWithinRange(logRecord.getDateTime(), fromDate, toDate))
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

