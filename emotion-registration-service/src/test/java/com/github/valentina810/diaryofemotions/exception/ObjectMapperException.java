package com.github.valentina810.diaryofemotions.exception;

public class ObjectMapperException extends RuntimeException {
    public ObjectMapperException(String message) {
        super("Возникла ошибка при преобразовании объекта " + message);
    }
}