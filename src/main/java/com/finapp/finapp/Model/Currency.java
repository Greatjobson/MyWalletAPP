package com.finapp.finapp.Model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum Currency {
    KZT,USD;

    @JsonCreator
    public static Currency from(String value) {
        return Arrays.stream(values())
                .filter(c -> c.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Incorrect currency. Use: KZT or USD")
                );
    }
}
