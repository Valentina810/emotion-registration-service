package com.github.valentina810.diaryofemotions.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Schema(description = "Сущность 'Список ошибок валидации'")
public class ValidationErrorResponse {
    private final List<Violation> violations;
}