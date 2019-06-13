package ru.testing.api.entities.translate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Detected {

    private String lang;
}