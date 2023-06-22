package com.github.valentina810.diaryofemotions.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmotionDto {
    @Schema(description = "Уникальный идентификатор")
    private Long id;
    @Schema(description = "Название")
    private String name;
    @Schema(description = "Url по которому хранится изображение")
    private String pictureUrl;
}