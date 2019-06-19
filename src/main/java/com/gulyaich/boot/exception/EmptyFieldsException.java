package com.gulyaich.boot.exception;

public class EmptyFieldsException extends RuntimeException {

    public EmptyFieldsException(String message) {
        super("Ошибка! Не заполнены обязательные поля: " + message);
    }
}
