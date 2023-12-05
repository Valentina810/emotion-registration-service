package com.github.valentina810.diaryofemotions.controller;

import com.github.valentina810.diaryofemotions.domain.dto.EmotionDto;
import com.github.valentina810.diaryofemotions.exception.MockServiceException;
import com.github.valentina810.diaryofemotions.exception.NotFoundException;
import com.github.valentina810.diaryofemotions.service.EmotionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PublicEmotionController.class)
class PublicEmotionControllerTest {

    @MockBean
    private EmotionService emotionService;

    @Autowired
    private MockMvc mvc;

    private final EmotionDto emotionDto1 = EmotionDto.builder()
            .id(1L)
            .name("Радость")
            .pictureUrl("https://emotions.ru/pictire/joy.png")
            .build();
    private final EmotionDto emotionDto2 = EmotionDto.builder()
            .id(2L)
            .name("Грусть")
            .pictureUrl("https://emotions.ru/pictire/sad.png")
            .build();

    @Test
    void getEmotionById_whenEmotionFound_thenReturnEmotion() {
        when(emotionService.getEmotion(anyLong()))
                .thenReturn(emotionDto1);

        try {
            mvc.perform(get("/public/emotions/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(emotionDto1.getId()), Long.class))
                    .andExpect(jsonPath("$.name", is(emotionDto1.getName())))
                    .andExpect(jsonPath("$.pictureUrl", is(emotionDto1.getPictureUrl())));
        } catch (Exception e) {
            throw new MockServiceException();
        }

        verify(emotionService, times(1)).getEmotion(1);
    }

    @Test
    void getEmotionById_whenEmotionNotFound_thenReturnNotFound() {
        when(emotionService.getEmotion(anyLong()))
                .thenThrow(new NotFoundException(""));

        try {
            mvc.perform(get("/public/emotions/1"))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            throw new MockServiceException();
        }

        verify(emotionService, times(1)).getEmotion(1);
    }

    @Test
    void getEmotions_whenEmotionsFound_thenReturnEmotions() {

        when(emotionService.getEmotions(anyInt(), anyInt()))
                .thenReturn(List.of(emotionDto1, emotionDto2));

        try {
            mvc.perform(get("/public/emotions"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].id", is(emotionDto1.getId()), Long.class))
                    .andExpect(jsonPath("$[0].name", is(emotionDto1.getName())))
                    .andExpect(jsonPath("$[0].pictureUrl", is(emotionDto1.getPictureUrl())))
                    .andExpect(jsonPath("$[1].id", is(emotionDto2.getId()), Long.class))
                    .andExpect(jsonPath("$[1].name", is(emotionDto2.getName())))
                    .andExpect(jsonPath("$[1].pictureUrl", is(emotionDto2.getPictureUrl())));
        } catch (Exception e) {
            throw new MockServiceException();
        }

        verify(emotionService, times(1)).getEmotions(0, 10);
    }

    @Test
    void getEmotions_whenEmotionsNotFound_thenReturnEmpty() {
        {
            try {
                mvc.perform(get("/public/emotions"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(0)));
            } catch (Exception e) {
                throw new MockServiceException();
            }
        }

        verify(emotionService, times(1)).getEmotions(0, 10);
    }
}