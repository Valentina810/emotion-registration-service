package com.github.valentina810.diaryofemotions.service;

import com.github.valentina810.diaryofemotions.domain.dto.EmotionCreateDto;
import com.github.valentina810.diaryofemotions.domain.dto.EmotionDto;

import java.util.List;

public interface EmotionService {
    EmotionDto addEmotion(EmotionCreateDto emotionCreateDto);

    void deleteEmotion(long emotionId);

    EmotionDto updateEmotion(long emotionId, EmotionCreateDto emotionCreateDto);

    EmotionDto getEmotion(long emotionId);

    List<EmotionDto> getEmotions(Integer from, Integer size);
}