package com.gulyaich.boot.exception;

public class StudentEmptyFieldsException extends RuntimeException {

    public StudentEmptyFieldsException(String fields) {
        super("Ошибка! Не заполнены обязательные поля: " + fields);
    }

}
