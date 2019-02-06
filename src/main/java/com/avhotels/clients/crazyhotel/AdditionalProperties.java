package com.avhotels.clients.crazyhotel;

public enum AdditionalProperties {

    RATE("rate"), DISCOUNT("discount");

    private String key;

    AdditionalProperties(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
