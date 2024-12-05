package com.erciyes.enums;

public enum ServiceType {
    SAC(60),
    SAKAl(30);

    private final int duration;

    ServiceType(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}
