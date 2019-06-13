package ru.testing.api;

public enum ServiceType {

    MICROSERVICE("https://translate.yandex.net/");

    private String baseUrl;

    ServiceType(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}