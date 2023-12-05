package com.github.valentina810.diaryofemotions.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Schema(description = "Сущность 'Эмоция'")
public class EmotionDto {
    @Schema(description = "Уникальный идентификатор", example = "1")
    private Long id;
    @Schema(description = "Название", example = "Гнев")
    private String name;
    @Schema(description = "Url по которому хранится изображение", example = "https://emotions.ru/pictire/angry.png")
    private String pictureUrl;
}