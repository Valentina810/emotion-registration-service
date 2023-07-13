package com.github.valentina810.diaryofemotions.mapper;

import com.github.valentina810.diaryofemotions.domain.dto.EmotionCreateDto;
import com.github.valentina810.diaryofemotions.domain.dto.EmotionDto;
import com.github.valentina810.diaryofemotions.domain.model.Emotion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmotionMapper {
    EmotionDto toEmotionDto(Emotion emotion);

    @Mapping(source = "emotion.name", target = "name")
    @Mapping(source = "emotion.pictureUrl", target = "pictureUrl")
    EmotionCreateDto toEmotionCreateDto(Emotion emotion);

    @Mapping(source = "emotionCreateDto.name", target = "name")
    @Mapping(source = "emotionCreateDto.pictureUrl", target = "pictureUrl")
    Emotion toEmotion(EmotionCreateDto emotionCreateDto);
}