package com.github.valentina810.diaryofemotions.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.valentina810.diaryofemotions.domain.dto.EmotionCreateDto;
import com.github.valentina810.diaryofemotions.domain.dto.EmotionDto;
import com.github.valentina810.diaryofemotions.exception.MockServiceException;
import com.github.valentina810.diaryofemotions.exception.ObjectMapperException;
import com.github.valentina810.diaryofemotions.services.EmotionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminEmotionController.class)
class AdminEmotionControllerTest {

    @MockBean
    private EmotionService emotionService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    private final EmotionDto emotionDto = EmotionDto.builder()
            .id(1L)
            .name("Радость")
            .pictureUrl("https://emotions.ru/pictire/joy.png")
            .build();

    private final EmotionCreateDto emotionCreateDto = EmotionCreateDto.builder()
            .name("Радость")
            .pictureUrl("https://emotions.ru/pictire/joy.png")
            .build();

    private final EmotionCreateDto emotionCreateDtoForUpdate = EmotionCreateDto.builder()
            .name("Радость")
            .pictureUrl("https://emotions.ru/pictire/new_joy.png")
            .build();

    private final EmotionDto emotionUpdateDto = EmotionDto.builder()
            .id(1L)
            .name("Радость")
            .pictureUrl("https://emotions.ru/pictire/new_joy.png")
            .build();

    @Test
    void createEmotion_whenEmotionCorrect_thenReturnEmotion() {
        when(emotionService.addEmotion(any()))
                .thenReturn(emotionDto);

        try {
            mvc.perform(post("/admin/emotions")
                            .content(mapper.writeValueAsString(emotionCreateDto))
                            .characterEncoding(StandardCharsets.UTF_8)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(emotionDto.getId()), Long.class))
                    .andExpect(jsonPath("$.name", is(emotionDto.getName())))
                    .andExpect(jsonPath("$.pictureUrl", is(emotionDto.getPictureUrl())));
        } catch (Exception e) {
            throw new ObjectMapperException(emotionCreateDto.toString());
        }

        verify(emotionService, times(1)).addEmotion(emotionCreateDto);
    }

    @Test
    void createEmotion_whenEmotionEmptyName_thenReturnStatusBadRequest() {
        emotionCreateDto.setName("");

        try {
            mvc.perform(post("/admin/emotions")
                            .content(mapper.writeValueAsString(emotionCreateDto))
                            .characterEncoding(StandardCharsets.UTF_8)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new ObjectMapperException(emotionCreateDto.toString());
        }

        verify(emotionService, times(0)).addEmotion(emotionCreateDto);
    }

    @Test
    void createEmotion_whenEmotionNullName_thenReturnStatusBadRequest() {
        emotionCreateDto.setName(null);

        try {
            mvc.perform(post("/admin/emotions")
                            .content(mapper.writeValueAsString(emotionCreateDto))
                            .characterEncoding(StandardCharsets.UTF_8)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new ObjectMapperException(emotionCreateDto.toString());
        }

        verify(emotionService, times(0)).addEmotion(emotionCreateDto);
    }

    @Test
    void createEmotion_whenEmotionEmptyPictureUrl_thenReturnStatusBadRequest() {
        emotionCreateDto.setPictureUrl("");

        try {
            mvc.perform(post("/admin/emotions")
                            .content(mapper.writeValueAsString(emotionCreateDto))
                            .characterEncoding(StandardCharsets.UTF_8)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new ObjectMapperException(emotionCreateDto.toString());
        }

        verify(emotionService, times(0)).addEmotion(emotionCreateDto);
    }

    @Test
    void createEmotion_whenEmotionNullPictureUrl_thenReturnStatusBadRequest() {
        emotionCreateDto.setPictureUrl(null);

        try {
            mvc.perform(post("/admin/emotions")
                            .content(mapper.writeValueAsString(emotionCreateDto))
                            .characterEncoding(StandardCharsets.UTF_8)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new ObjectMapperException(emotionCreateDto.toString());
        }

        verify(emotionService, times(0)).addEmotion(emotionCreateDto);
    }

    @Test
    void deleteEmotion_whenEmotionExists_thenDeleteEmotion() {
        try {
            mvc.perform(delete("/admin/emotions/1"))
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            throw new MockServiceException();
        }

        verify(emotionService, times(1)).deleteEmotion(1);
    }

    @Test
    void updateEmotion_whenCorrectData_thenUpdateEmotion() {
        when(emotionService.updateEmotion(anyLong(), any()))
                .thenReturn(emotionUpdateDto);

        try {
            mvc.perform(patch("/admin/emotions/1")
                            .content(mapper.writeValueAsString(emotionCreateDtoForUpdate))
                            .characterEncoding(StandardCharsets.UTF_8)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(emotionUpdateDto.getId()), Long.class))
                    .andExpect(jsonPath("$.name", is(emotionUpdateDto.getName())))
                    .andExpect(jsonPath("$.pictureUrl", is(emotionUpdateDto.getPictureUrl())));
        } catch (Exception e) {
            throw new ObjectMapperException(emotionCreateDtoForUpdate.toString());
        }

        verify(emotionService, times(1)).updateEmotion(1, emotionCreateDtoForUpdate);
    }

    @Test
    void updateEmotion_whenInCorrectData_thenReturnStatusBadRequest() {
        try {
            mvc.perform(patch("/admin/emotions/1")
                            .content(mapper.writeValueAsString(emotionCreateDtoForUpdate).replace(",", ""))
                            .characterEncoding(StandardCharsets.UTF_8)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {

            throw new ObjectMapperException(emotionCreateDtoForUpdate.toString());
        }

        verify(emotionService, times(0)).updateEmotion(1, emotionCreateDtoForUpdate);
    }

    @Test
    void updateEmotion_whenInCorrectIdEmotion_thenReturnStatusBadRequest() {
        try {
            mvc.perform(patch("/admin/emotions/t")
                            .content(mapper.writeValueAsString(emotionCreateDtoForUpdate))
                            .characterEncoding(StandardCharsets.UTF_8)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new ObjectMapperException(emotionCreateDtoForUpdate.toString());
        }

        verify(emotionService, times(0)).updateEmotion(1, emotionCreateDtoForUpdate);
    }
}