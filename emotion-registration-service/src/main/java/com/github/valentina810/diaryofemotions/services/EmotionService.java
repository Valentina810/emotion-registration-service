package com.github.valentina810.diaryofemotions.services;

import com.github.valentina810.diaryofemotions.domain.dto.EmotionCreateDto;
import com.github.valentina810.diaryofemotions.domain.dto.EmotionDto;

public interface EmotionService {
    EmotionDto saveEmotion(EmotionCreateDto emotionCreateDto);

    void deleteEmotion(long emotionId);

    EmotionDto updateEmotion(long emotionId, EmotionCreateDto emotionCreateDto);
}