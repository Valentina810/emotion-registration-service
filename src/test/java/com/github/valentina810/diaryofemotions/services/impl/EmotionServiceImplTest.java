package com.github.valentina810.diaryofemotions.services.impl;

import com.github.valentina810.diaryofemotions.domain.dto.EmotionCreateDto;
import com.github.valentina810.diaryofemotions.domain.dto.EmotionDto;
import com.github.valentina810.diaryofemotions.domain.model.Emotion;
import com.github.valentina810.diaryofemotions.mapper.EmotionMapper;
import com.github.valentina810.diaryofemotions.repositories.EmotionRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class EmotionServiceImplTest {

    @Spy
    private EmotionMapper emotionMapper = Mappers.getMapper(EmotionMapper.class);
    @Mock
    private EmotionRepository emotionRepository;
    @InjectMocks
    private EmotionServiceImpl emotionServiceImpl;

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

        emotionServiceImpl.addEmotion(emotionCreateDto);

        InOrder orderVerifier = Mockito.inOrder(emotionRepository);
        orderVerifier.verify(emotionRepository).findByName(emotion.getName());
        orderVerifier.verify(emotionRepository).save(emotion);
    }

    @Test
    void deleteEmotion() {
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