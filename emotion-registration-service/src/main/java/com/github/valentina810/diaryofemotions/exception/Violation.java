package com.github.valentina810.diaryofemotions.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "Сущность 'Сообщение об ошибке валидации'")
public class Violation {
    @Schema(description = "Имя поля", example = "name")
    private final String fieldName;
    @Schema(description = "Причина ошибки валидации", example = "не должно быть пустым")
    private final String message;
}