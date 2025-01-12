package com.biswamit.springboot.jpa.rest.db.type;

import jakarta.persistence.AttributeConverter;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

//@Converter(autoApply = true)
public class ZdtPropertyConverter implements AttributeConverter<ZonedDateTime, String> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSzz");

    @Override
    public String convertToDatabaseColumn(ZonedDateTime attribute) {
        try {
            String zdtFormattedString = attribute.format(DATE_TIME_FORMATTER);
            return zdtFormattedString;
        } catch (final Exception exp) {
            throw new RuntimeException("Failed to convert ZoneDateTime to String: " + exp.getMessage(), exp);
        }
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null) {
                throw new IOException("ZoneDateTime argument is null.");
            }
            ZonedDateTime zdt = ZonedDateTime.parse(dbData, DATE_TIME_FORMATTER.withZone(ZoneId.systemDefault()));
            return zdt;
        } catch (final Exception exp) {
            throw new RuntimeException("Failed to convert String to ZoneDateTime : " + exp.getMessage(), exp);
        }
    }
}
