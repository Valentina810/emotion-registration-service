package com.github.valentina810.diaryofemotions.service.impl;

import com.github.valentina810.diaryofemotions.domain.dto.EmotionCreateDto;
import com.github.valentina810.diaryofemotions.domain.dto.EmotionDto;
import com.github.valentina810.diaryofemotions.domain.model.Emotion;
import com.github.valentina810.diaryofemotions.exception.NotFoundException;
import com.github.valentina810.diaryofemotions.mapper.EmotionMapper;
import com.github.valentina810.diaryofemotions.repository.EmotionRepository;
import com.github.valentina810.diaryofemotions.service.EmotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
@RequiredArgsConstructor
@Slf4j
public class EmotionServiceImpl implements EmotionService {
    private final EmotionRepository emotionRepository;
    private final EmotionMapper emotionMapper;

    @Override
    public EmotionDto addEmotion(EmotionCreateDto emotionCreateDto) {
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
        if (emotionCreateDto.equals(emotionMapper.toEmotionCreateDto(emotion))) {
            log.info("Эмоция {} не была изменена, так как поля объекта совпадают с ранее сохранёнными", emotion);
            return emotionMapper.toEmotionDto(emotion);
        } else {
            if (emotionCreateDto.getName() != null && !emotionCreateDto.getName().isBlank()) {
                emotion.setName(emotionCreateDto.getName());
            }
            if (emotionCreateDto.getPictureUrl() != null && !emotionCreateDto.getPictureUrl().isBlank()) {
                emotion.setPictureUrl(emotionCreateDto.getPictureUrl());
            }
            EmotionDto emotionDto = emotionMapper.toEmotionDto(emotionRepository.save(emotion));
            log.info("Изменена эмоция {}", emotionDto);
            return emotionDto;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public EmotionDto getEmotion(long emotionId) {
        EmotionDto emotion = emotionMapper.toEmotionDto(emotionRepository.findById(emotionId).orElseThrow(() ->
                new NotFoundException(String.format("Эмоция с id %d не найдена", emotionId))));
        log.info("Получена эмоция {}", emotion);
        return emotion;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmotionDto> getEmotions(Integer from, Integer size) {
        List<EmotionDto> emotions = emotionRepository
                .findAll(PageRequest.of(from / size, size))
                .stream().map(emotionMapper::toEmotionDto)
                .collect(Collectors.toList());
        log.info("Получен список эмоций {}", emotions);
        return emotions;
    }
}