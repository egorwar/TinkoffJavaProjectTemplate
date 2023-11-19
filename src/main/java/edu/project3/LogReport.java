package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings({"MultipleStringLiterals", "MagicNumber"})
class LogReport {
    private final String path;
    private final OffsetDateTime fromDate;
    private final OffsetDateTime toDate;
    private final List<LogRecord> logRecords;

    private final int maxCount = 5;

    LogReport(String path, OffsetDateTime fromDate, OffsetDateTime toDate) {
        logRecords = new ArrayList<>();
        this.path = path;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public void addLogRecord(LogRecord logRecord) {
        logRecords.add(logRecord);
    }

    public String toMarkdown() {
        StringBuilder sb = new StringBuilder();

        sb.append("#### Общая информация\n\n");
        sb.append("| Метрика                  | Значение   |\n");
        sb.append("|:-------------------------|-----------:|\n");
        sb.append("| Путь                     | `").append(path).append("` |\n");
        sb.append("| Начальная дата           | ")
            .append(fromDate != null ? fromDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) : "-").append(" |\n");
        sb.append("| Конечная дата            | ")
            .append(toDate != null ? toDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) : "-").append(" |\n");
        sb.append("| Количество запросов      | ").append(logRecords.size()).append(" |\n");
        sb.append("| Средний размер ответа    | ").append(calculateAverageResponseSize()).append("b |\n\n");

        int i = 0;
        sb.append("#### Запрашиваемые ресурсы (TOP " + maxCount + ")\n\n");
        sb.append("| Ресурс                   | Количество |\n");
        sb.append("|:-------------------------|-----------:|\n");
        for (Map.Entry<String, Integer> entry : calculateMostFrequentResources().entrySet()) {
            sb.append("| ").append(entry.getKey()).append(" | ").append(entry.getValue()).append(" |\n");
            i++;
            if (i >= maxCount) {
                break;
            }
        }
        sb.append("\n");

        sb.append("#### Коды ответа\n\n");
        sb.append("| Код  | Имя                    | Количество |\n");
        sb.append("|:----:|:-----------------------|-----------:|\n");
        for (var entry : calculateMostFrequentStatusCodes()) {
            sb
                .append("| ")
                .append(entry.getKey())
                .append(" | ")
                .append(getStatusName(entry.getKey()))
                .append(" | ")
                .append(entry.getValue())
                .append(" |\n");
        }
        sb.append("\n");

        i = 0;
        sb.append("#### ip адреса (TOP " + maxCount + ")\n\n");
        sb.append("| Адрес| Частота обращения |\n");
        sb.append("|:----:|------------------:|\n");
        for (var entry : calculateMostFrequentIpAddresses()) {
            sb
                .append("| ")
                .append(entry.getKey())
                .append(" | ")
                .append(entry.getValue())
                .append(" |\n");
            i++;
            if (i >= maxCount) {
                break;
            }
        }

        sb.append("\n#### типы запросов\n\n");
        sb.append("| Запрос| Количество|\n");
        sb.append("|:-----:|----------:|\n");
        for (var entry : calculateMostFrequentRequestTypes()) {
            sb
                .append("| ")
                .append(entry.getKey())
                .append(" | ")
                .append(entry.getValue())
                .append(" |\n");
        }

        return sb.toString();
    }

    public String toAsciiDoc() {
        StringBuilder sb = new StringBuilder();

        sb.append("==== Общая информация ====\n\n");
        sb.append("|=====\n");
        sb.append("|Метрика | Значение\n");
        sb.append("|Файл(-ы) | `").append(path).append("`\n");
        sb.append("|Начальная дата | ")
            .append(fromDate != null ? fromDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) : "-").append("\n");
        sb.append("|Конечная дата | ")
            .append(toDate != null ? toDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) : "-").append("\n");
        sb.append("|Количество запросов | ").append(logRecords.size()).append("\n");
        sb.append("|Средний размер ответа | ").append(calculateAverageResponseSize()).append("b\n");
        sb.append("|=====\n");
        sb.append("\n");

        sb.append("==== Запрашиваемые ресурсы (TOP " + maxCount + ") ====\n\n");
        sb.append("|=====\n");
        sb.append("|Ресурс | Количество\n");
        int i = 0;
        for (Map.Entry<String, Integer> entry : calculateMostFrequentResources().entrySet()) {
            sb.append("|").append(entry.getKey()).append(" | ").append(entry.getValue()).append("\n");
            i++;
            if (i >= maxCount) {
                break;
            }
        }
        sb.append("|=====\n");
        sb.append("\n");

        sb.append("==== Коды ответа ====\n\n");
        sb.append("|=====\n");
        sb.append("|Код | Имя | Количество\n");
        for (var entry : calculateMostFrequentStatusCodes()) {
            sb
                .append("|")
                .append(entry.getKey())
                .append(" | ")
                .append(getStatusName(entry.getKey()))
                .append(" | ")
                .append(entry.getValue())
                .append("\n");
        }
        sb.append("|=====\n");

        sb.append("\n");

        i = 0;
        sb.append("==== ip адреса (TOP " + maxCount + ") ====\n\n");

        sb.append("|=====\n");
        sb.append("|Адрес | Частота обращения\n");
        for (var entry : calculateMostFrequentIpAddresses()) {
            sb
                .append("| ")
                .append(entry.getKey())
                .append(" | ")
                .append(entry.getValue())
                .append("\n");
            i++;
            if (i >= maxCount) {
                break;
            }
        }
        sb.append("|=====\n");

        sb.append("\n==== типы запросов ====\n\n");

        sb.append("|=====\n");
        sb.append("|Запрос | Количество\n");
        for (var entry : calculateMostFrequentRequestTypes()) {
            sb
                .append("| ")
                .append(entry.getKey())
                .append(" | ")
                .append(entry.getValue())
                .append("\n");
        }
        sb.append("|=====\n");

        return sb.toString();
    }

    private int calculateAverageResponseSize() {
        if (logRecords.isEmpty()) {
            return 0;
        }

        int totalSize = 0;
        for (LogRecord logRecord : logRecords) {
            totalSize += logRecord.getBytesSent();
        }

        return totalSize / logRecords.size();
    }

    private Map<String, Integer> calculateMostFrequentResources() {
        Map<String, Integer> resources = new HashMap<>();

        for (LogRecord logRecord : logRecords) {
            String resource = getResourceFromRequest(logRecord.getRequest());
            resources.put(resource, resources.getOrDefault(resource, 0) + 1);
        }

        return sortStringIntByValueDescending(resources);
    }

    private List<Map.Entry<Integer, Long>> calculateMostFrequentStatusCodes() {
        var freqs = logRecords.stream().collect(
            Collectors.groupingBy(
                LogRecord::getStatus,
                Collectors.counting()
            )
        );

        return freqs.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(Collectors.toList());
    }

    private List<Map.Entry<String, Long>> calculateMostFrequentIpAddresses() {
        var freqs = logRecords.stream().collect(
            Collectors.groupingBy(
                LogRecord::getIpAddress,
                Collectors.counting()
            )
        );

        return freqs.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(Collectors.toList());
    }

    private List<Map.Entry<String, Long>> calculateMostFrequentRequestTypes() {
        var freqs = logRecords.stream().collect(
            Collectors.groupingBy(
                LogRecord::getRequestType,
                Collectors.counting()
            )
        );

        return freqs.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(Collectors.toList());
    }

    private Map<String, Integer> sortStringIntByValueDescending(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private String getResourceFromRequest(String request) {
        String[] parts = request.split(" ");
        if (parts.length >= 2) {
            return parts[1];
        }
        return "";
    }

    private String getStatusName(int statusCode) {
        return switch (statusCode) {
            case 200 -> "OK";
            case 400 -> "Bad Request";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            default -> "?";
        };
    }
}
