package com.gulyaich.boot.exception;

public class StudentErrorSymbolsException extends RuntimeException {

    public StudentErrorSymbolsException(String fields) {
        super("Ошибка! Проверьте правильность символов в полях: " + fields);
    }
}
