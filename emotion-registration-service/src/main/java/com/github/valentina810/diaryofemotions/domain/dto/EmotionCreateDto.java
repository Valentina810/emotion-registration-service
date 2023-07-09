package com.github.valentina810.diaryofemotions.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Schema(description = "Сущность 'Эмоция'")
public class EmotionCreateDto {
    @NotNull
    @NotBlank
    @Schema(description = "Название", example = "Гнев")
    private String name;

    @NotNull
    @NotBlank
    @Schema(description = "Url по которому хранится изображение", example = "https://emotions.ru/pictire/angry.png")
    private String pictureUrl;
}