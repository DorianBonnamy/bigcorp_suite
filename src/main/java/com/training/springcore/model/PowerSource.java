package com.training.springcore.model;

public enum PowerSource {
    FIXED,
    REAL,
    SIMULATED;

    static PowerSource of(String power) {
        if (power == null) {
            return null;
        }
        return PowerSource.valueOf(power);
    }
}
