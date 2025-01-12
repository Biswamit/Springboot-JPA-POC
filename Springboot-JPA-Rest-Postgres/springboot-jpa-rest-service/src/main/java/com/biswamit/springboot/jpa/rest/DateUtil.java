package com.biswamit.springboot.jpa.rest;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER_WITH6S2Z
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSzz");

    private static final DateTimeFormatter DATE_TIME_FORMATTER_WITH3S2Z
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSzz");

    public static String fromZDTtoStringWithDTF6S2Z(ZonedDateTime zonedDateTimeValue) throws IOException {
        if (zonedDateTimeValue == null) {
            throw new IOException("ZonedDateTime argument is null.");
        }
        return zonedDateTimeValue.format(DATE_TIME_FORMATTER_WITH6S2Z);
    }

    public static ZonedDateTime fromStringToZDTWithDTF6S2Z(String dateAsStringValue) throws IOException {
        if (dateAsStringValue == null) {
            throw new IOException("ZoneDateTime argument is null.");
        }
        ZonedDateTime zdt = ZonedDateTime.parse(dateAsStringValue, DATE_TIME_FORMATTER_WITH6S2Z.withZone(ZoneId.systemDefault()));
        return zdt;
    }

    public static String fromZDTtoStringWith3S2Z(ZonedDateTime zonedDateTimeValue) throws IOException {
        if (zonedDateTimeValue == null) {
            throw new IOException("ZonedDateTime argument is null.");
        }
        return zonedDateTimeValue.format(DATE_TIME_FORMATTER_WITH3S2Z);
    }

    public static ZonedDateTime fromStringToZDTWith3S2Z(String dateAsStringValue) throws IOException {
        if (dateAsStringValue == null) {
            throw new IOException("ZoneDateTime argument is null.");
        }
        ZonedDateTime zdt = ZonedDateTime.parse(dateAsStringValue, DATE_TIME_FORMATTER_WITH3S2Z.withZone(ZoneId.systemDefault()));
        return zdt;
    }
}
