package com.biswamit.springboot.jpa.rest.constant;

import java.util.EnumSet;

public enum Condition {
    AND(0), OR(1);

    private int valueInt;

    private Condition(int var) {
        this.valueInt = var;
    }

    public int intValue() {
        return this.valueInt;
    }

    public static Condition find(int var0) {
        return (Condition) EnumSet.allOf(Condition.class).stream().filter((var1) -> {
            return var1.intValue() == var0;
        }).findFirst().orElse(null);
    }
}
