package com.avhotels.clients.besthotel;

public enum AdditionalProperties {

    RATE("rate");

    private String key;

    AdditionalProperties(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
