package com.github.valentina810.diaryofemotions.services.impl;

import com.github.valentina810.diaryofemotions.domain.dto.EmotionCreateDto;
import com.github.valentina810.diaryofemotions.domain.dto.EmotionDto;
import com.github.valentina810.diaryofemotions.domain.model.Emotion;
import com.github.valentina810.diaryofemotions.exception.NotFoundException;
import com.github.valentina810.diaryofemotions.mapper.EmotionMapper;
import com.github.valentina810.diaryofemotions.repositories.EmotionRepository;
import com.github.valentina810.diaryofemotions.services.EmotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
@RequiredArgsConstructor
@Slf4j
public class EmotionServiceImpl implements EmotionService {
    private final EmotionRepository emotionRepository;
    private final EmotionMapper emotionMapper;

    @Override
    public EmotionDto saveEmotion(EmotionCreateDto emotionCreateDto) {
        if (emotionRepository.findByName(emotionCreateDto.getName()).isPresent()) {
            throw new ConstraintViolationException(String.format("Эмоция с названием '" + emotionCreateDto.getName() + "' уже существует"),
                    new SQLException(), emotionCreateDto.getName());
        }
        EmotionDto emotionDto = emotionMapper.toEmotionDto(emotionRepository.save(emotionMapper.toEmotion(emotionCreateDto)));
        log.info("Добавлена новая эмоция {}", emotionDto);
        return emotionDto;
    }

    @Override
    public void deleteEmotion(long emotionId) {
        emotionRepository.delete(emotionRepository.findById(emotionId).orElseThrow(() ->
                new NotFoundException(String.format("Эмоция с id %d не найдена", emotionId))));
        log.info("Эмоция с id {} удалена", emotionId);
    }

    @Override
    public EmotionDto updateEmotion(long emotionId, EmotionCreateDto emotionCreateDto) {
        Emotion emotion = emotionRepository.findById(emotionId).orElseThrow(() ->
                new NotFoundException(String.format("Эмоция с id %d не найдена", emotionId)));
        emotion.setName(emotionCreateDto.getName());
        emotion.setPictureUrl(emotionCreateDto.getPictureUrl());
        EmotionDto emotionDto = emotionMapper.toEmotionDto(emotionRepository.save(emotion));
        log.info("Изменена эмоция {}", emotionDto);
        return emotionDto;
    }
}