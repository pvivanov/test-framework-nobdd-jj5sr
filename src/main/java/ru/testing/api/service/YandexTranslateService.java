package ru.testing.api.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.testing.api.ServiceBuilder;
import ru.testing.api.entities.translate.TranslateResponse;

import javax.annotation.PostConstruct;


@Slf4j
@Component
public class YandexTranslateService {

    @Autowired
    private ServiceBuilder serviceBuilder;

    private YandexTranslateManagement yandexTranslateManagement;
    private static final String TOKEN = ""; //TODO generate and insert your yandex token here, https://tech.yandex.ru/translate/doc/dg/concepts/api-keys-docpage/

    @PostConstruct
    public void init() {
        yandexTranslateManagement = serviceBuilder
                .build(YandexTranslateManagement.class);
    }

    @SneakyThrows
    public TranslateResponse getTranslateResponse(String text) {
        return yandexTranslateManagement.getTranslateText(TOKEN, text, "en-ru","plain", "1" ).execute().body();
    }
}