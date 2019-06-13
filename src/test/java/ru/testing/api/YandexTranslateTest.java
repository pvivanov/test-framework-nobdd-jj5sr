package ru.testing.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.testing.api.entities.translate.TranslateResponse;
import ru.testing.api.service.YandexTranslateService;

@SpringBootTest(classes = Application.class)
public class YandexTranslateTest {

    @Autowired
    private YandexTranslateService translateGateway;

    @ParameterizedTest
    @ValueSource(strings = { "Hello World!" })
    @DisplayName("Text translation with API yandex translator")
    public void translateText(String textToTranslate) {
        TranslateResponse translateTextResponse = translateGateway.getTranslateResponse(textToTranslate);
        Assertions.assertEquals("Всем Привет!", translateTextResponse.getText().get(0));
    }
}