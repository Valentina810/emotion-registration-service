package com.github.valentina810.diaryofemotions.service.impl;

import com.github.valentina810.diaryofemotions.domain.dto.EmotionCreateDto;
import com.github.valentina810.diaryofemotions.domain.dto.EmotionDto;
import com.github.valentina810.diaryofemotions.domain.model.Emotion;
import com.github.valentina810.diaryofemotions.exception.NotFoundException;
import com.github.valentina810.diaryofemotions.mapper.EmotionMapper;
import com.github.valentina810.diaryofemotions.repository.EmotionRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class EmotionServiceImplTest {

    @Spy
    private EmotionMapper emotionMapper = Mappers.getMapper(EmotionMapper.class);
    @Mock
    private EmotionRepository emotionRepository;
    @InjectMocks
    private EmotionServiceImpl emotionService;

    private final Emotion emotion = Emotion.builder()
            .name("Радость")
            .pictureUrl("https://emotions.ru/pictire/joy.png").build();

    private final EmotionDto emotionDto = EmotionDto.builder()
            .name("Радость")
            .pictureUrl("https://emotions.ru/pictire/joy.png").build();

    private final EmotionCreateDto emotionCreateDto = EmotionCreateDto.builder()
            .name("Радость")
            .pictureUrl("https://emotions.ru/pictire/joy.png").build();


    @Test
    void addEmotion_whenEmotionCorrect_thenReturnEmotion() {
        when(emotionRepository.findByName(emotion.getName()))
                .thenReturn(Optional.empty());
        when(emotionRepository.save(any()))
                .thenReturn(emotion);

        emotionService.addEmotion(emotionCreateDto);

        InOrder orderVerifier = Mockito.inOrder(emotionRepository);
        orderVerifier.verify(emotionRepository).findByName(emotion.getName());
        orderVerifier.verify(emotionRepository).save(emotion);
    }

    @Test
    void addEmotion_whenEmotionInCorrectName_thenReturnException() {
        when(emotionRepository.findByName(emotion.getName()))
                .thenReturn(Optional.of(emotion));
        ConstraintViolationException thrown = Assertions.assertThrows(ConstraintViolationException.class,
                () -> emotionService.addEmotion(emotionCreateDto));

        Assertions.assertEquals("Эмоция с названием '" + emotionCreateDto.getName()
                + "' уже существует", thrown.getMessage());
        verify(emotionRepository).findByName(emotion.getName());
        verify(emotionRepository, never()).save(emotion);
    }

    @Test
    void deleteEmotion_whenEmotionFound_thenDeleteSuccessful() {
        when(emotionRepository.findById(any()))
                .thenReturn(Optional.of(emotion));

        emotionService.deleteEmotion(1);
        verify(emotionRepository, atLeastOnce()).findById(1L);
        verify(emotionRepository, atLeastOnce()).delete(emotion);
    }

    @Test
    void deleteEmotion_whenEmotionNotFound_thenReturnException() {
        when(emotionRepository.findById(1L))
                .thenReturn(Optional.empty());
        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class,
                () -> emotionService.deleteEmotion(1L));

        Assertions.assertEquals("Эмоция с id 1 не найдена", thrown.getMessage());
        verify(emotionRepository, atLeastOnce()).findById(1L);
        verify(emotionRepository, never()).save(emotion);
    }

    @Test
    void updateEmotion() {
    }

    @Test
    void getEmotion() {
    }

    @Test
    void getEmotions() {
    }
}