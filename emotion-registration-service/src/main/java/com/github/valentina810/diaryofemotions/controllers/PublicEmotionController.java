package com.github.valentina810.diaryofemotions.controllers;

import com.github.valentina810.diaryofemotions.domain.dto.EmotionDto;
import com.github.valentina810.diaryofemotions.services.EmotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/public/emotions")
@Validated
@Tag(name = "Получение информации из справочника Эмоции")
public class PublicEmotionController {
    private final EmotionService emotionService;

    @GetMapping("/{emotionId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение эмоции по id")
    public EmotionDto getEmotion(@PathVariable @Parameter(description = "Идентификатор эмоции") long emotionId) {
        return emotionService.getEmotion(emotionId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение списка эмоций постранично")
    public List<EmotionDto> getEmotions(@PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
                                        @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return emotionService.getEmotions(from, size);
    }
}