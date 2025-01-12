package com.biswamit.springboot.jpa.rest.constant;

import java.util.EnumSet;

public enum LogEntry {
    SPRING_BOOT_JPA_LOG("SB-JPA-");

    private String prefixStr;

    LogEntry(String prefixStr) {
        this.prefixStr = prefixStr;
    }

    public String getPrefix() {
        return this.prefixStr;
    }

    public static LogEntry find(String variable) {
        return (LogEntry) EnumSet.allOf(LogEntry.class).stream().filter((var) -> {
            return var.prefixStr.equals(variable);
        }).findFirst().orElse(null);
    }
}
