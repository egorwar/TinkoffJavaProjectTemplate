package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class LogRecord {
    private final String ipAddress;
    private final String datetime;
    private final String request;
    private final int status;
    private final int bytesSent;
    private final String referer;
    private final String userAgent;

    LogRecord(
        String ipAddress,
        String datetime,
        String request,
        int status,
        int bytesSent,
        String referer,
        String userAgent
    ) {
        this.ipAddress = ipAddress;
        this.datetime = datetime;
        this.request = request;
        this.status = status;
        this.bytesSent = bytesSent;
        this.referer = referer;
        this.userAgent = userAgent;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public OffsetDateTime getDateTime() {
        return OffsetDateTime.parse(datetime, DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH));
    }

    public String getRequest() {
        return request;
    }

    public String getRequestType() {
        return request.split(" ")[0];
    }

    public int getStatus() {
        return status;
    }

    public int getBytesSent() {
        return bytesSent;
    }
}
