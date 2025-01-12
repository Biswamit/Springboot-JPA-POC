package com.biswamit.springboot.jpa.rest.constant;

import java.util.EnumSet;

public enum Log {
    TRACE(0), DEBUG(1), INFO(2), WARN(3), ERROR(4);

    private int levelInt;

    Log(int var) {
        this.levelInt = levelInt;
    }

    public int getLevel() {
        return this.levelInt;
    }

    public static Log find(int variable) {
        return (Log) EnumSet.allOf(Log.class).stream().filter((var) -> {
            return var.levelInt == variable;
        }).findFirst().orElse(null);
    }
}
