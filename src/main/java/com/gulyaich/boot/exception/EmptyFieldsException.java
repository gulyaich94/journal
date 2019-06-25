package com.gulyaich.boot.exception;

import com.gulyaich.boot.entity.Student;

import static org.apache.logging.log4j.util.Strings.isBlank;

public class EmptyFieldsException extends RuntimeException {

    public EmptyFieldsException(String message) {
        super("Ошибка! Не заполнены обязательные поля: " + message);
    }

    public static EmptyFieldsException createEmptyFieldsException(Student student) {
        StringBuilder errorMessage = new StringBuilder();
        if (isBlank(student.getFirstName())) {
            errorMessage.append(" имя");
        }
        if (isBlank(student.getLastName())) {
            errorMessage.append(" фамилия");
        }
        return new EmptyFieldsException(errorMessage.toString());

    }
}
