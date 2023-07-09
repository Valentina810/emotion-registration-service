package com.github.valentina810.diaryofemotions.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
@Schema(description = "Сущность 'Сообщение об ошибке'")
public final class ErrorResponse {
    @Schema(description = "Статус", example = "Conflict")
    private final String status;
    @Schema(description = "Причина ошибки", example = "Эмоция с названием 'Гнев' уже существует")
    private final String reason;
    @Schema(description = "Метка времени получения ошибки", example = "2023-06-26T13:04:35.539Z")
    private final LocalDateTime timestamp;
}