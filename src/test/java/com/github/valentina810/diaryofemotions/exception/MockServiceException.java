package com.github.valentina810.diaryofemotions.exception;

public class MockServiceException extends RuntimeException {
    public MockServiceException() {
        super("Возникла ошибка при получении ответа от сервиса emotionService");
    }
}