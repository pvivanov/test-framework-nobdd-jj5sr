package ru.testing.api.entities.translate;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TranslateResponse {

    private Integer code;
    private Detected detected;
    private String lang;
    private List<String> text;
}