package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class LogReport {
    private String fileName;
    private OffsetDateTime fromDate;
    private OffsetDateTime toDate;
    private final List<LogRecord> logRecords;

    public LogReport() {
        logRecords = new ArrayList<>();
    }

    public void addLogRecord(LogRecord logRecord) {
        logRecords.add(logRecord);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFromDate(OffsetDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(OffsetDateTime toDate) {
        this.toDate = toDate;
    }

    public String toMarkdown() {
        StringBuilder sb = new StringBuilder();

        sb.append("#### Общая информация\n\n");
        sb.append("| Метрика                  | Значение   |\n");
        sb.append("|:-------------------------|-----------:|\n");
        sb.append("| Файл(-ы)                 | `").append(fileName).append("` |\n");
        sb.append("| Начальная дата           | ").append(fromDate != null ? fromDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "-").append(" |\n");
        sb.append("| Конечная дата            | ").append(toDate != null ? toDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "-").append(" |\n");
        sb.append("| Количество запросов      | ").append(logRecords.size()).append(" |\n");
        sb.append("| Средний размер ответа    | ").append(calculateAverageResponseSize()).append("b |\n\n");

        sb.append("#### Запрашиваемые ресурсы\n\n");
        sb.append("| Ресурс                   | Количество |\n");
        sb.append("|:-------------------------|-----------:|\n");
        for (Map.Entry<String, Integer> entry : calculateMostFrequentResources().entrySet()) {
            sb.append("| ").append(entry.getKey()).append(" | ").append(entry.getValue()).append(" |\n");
        }
        sb.append("\n");

        sb.append("#### Коды ответа\n\n");
        sb.append("| Код  | Имя                    | Количество |\n");
        sb.append("|:----:|:-----------------------|-----------:|\n");
        for (Map.Entry<Integer, String> entry : calculateMostFrequentStatusCodes().entrySet()) {
            sb.append("| ").append(entry.getKey()).append(" | ").append(entry.getValue()).append(" | ").append(calculateStatusCodeCount(entry.getKey())).append(" |\n");
        }

        return sb.toString();
    }

    public String toAsciiDoc() {
        StringBuilder sb = new StringBuilder();

        sb.append("==== Общая информация ====\n\n");
        sb.append("|=====\n");
        sb.append("|Метрика | Значение\n");
        sb.append("|Файл(-ы) | `").append(fileName).append("`\n");
        sb.append("|Начальная дата | ").append(fromDate != null ? fromDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "-").append("\n");
        sb.append("|Конечная дата | ").append(toDate != null ? toDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "-").append("\n");
        sb.append("|Количество запросов | ").append(logRecords.size()).append("\n");
        sb.append("|Средний размер ответа | ").append(calculateAverageResponseSize()).append("b\n");
        sb.append("\n");

        sb.append("==== Запрашиваемые ресурсы ====\n\n");
        sb.append("|=====\n");
        sb.append("|Ресурс | Количество\n");
        for (Map.Entry<String, Integer> entry : calculateMostFrequentResources().entrySet()) {
            sb.append("|").append(entry.getKey()).append(" | ").append(entry.getValue()).append("\n");
        }
        sb.append("\n");

        sb.append("==== Коды ответа ====\n\n");
        sb.append("|=====\n");
        sb.append("|Код | Имя | Количество\n");
        for (Map.Entry<Integer, String> entry : calculateMostFrequentStatusCodes().entrySet()) {
            sb.append("|").append(entry.getKey()).append(" | ").append(entry.getValue()).append(" | ").append(calculateStatusCodeCount(entry.getKey())).append("\n");
        }

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

        return sortByValueDescending(resources);
    }

    private Map<Integer, String> calculateMostFrequentStatusCodes() {
        Map<Integer, String> statusCodes = new HashMap<>();

        for (LogRecord logRecord : logRecords) {
            int statusCode = logRecord.getStatus();
            String statusName = getStatusName(statusCode);
            statusCodes.put(statusCode, statusName);
        }

        return sortByKeyDescending(statusCodes);
    }

    private int calculateStatusCodeCount(int statusCode) {
        int count = 0;
        for (LogRecord logRecord : logRecords) {
            if (logRecord.getStatus() == statusCode) {
                count++;
            }
        }
        return count;
    }

    private Map<String, Integer> sortByValueDescending(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private Map<Integer, String> sortByKeyDescending(Map<Integer, String> map) {
        List<Map.Entry<Integer, String>> entries = new ArrayList<>(map.entrySet());
        entries.sort((e1, e2) -> e2.getKey().compareTo(e1.getKey()));

        Map<Integer, String> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, String> entry : entries) {
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
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            default -> "Unknown";
        };
    }
}
