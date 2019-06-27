package com.gulyaich.boot.service;

import com.gulyaich.boot.entity.Student;
import com.gulyaich.boot.exception.StudentEmptyFieldsException;
import com.gulyaich.boot.exception.StudentErrorSymbolsException;

import java.util.regex.Pattern;

import static org.apache.logging.log4j.util.Strings.isBlank;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

public class StudentUtils {

    private static Pattern ALL_RUSSIAN_LETTERS = Pattern.compile("^[а-яА-Я-]+$");
    private static String FIRST_NAME_FIELD = "имя";
    private static String LAST_NAME_FIELD = "фамилия";
    private static String COMMA = ",";

    public static void checkRequiredFields(Student student) {
        String emptyRequiredFields = getRequiredFieldsEmpty(student);
        String fieldsContainsErrorSymbols = getFieldsContainsErrorSymbols(student);
        if (isNotBlank(emptyRequiredFields)) {
            throw new StudentEmptyFieldsException(emptyRequiredFields);
        }
        if (isNotBlank(fieldsContainsErrorSymbols)) {
            throw new StudentErrorSymbolsException(fieldsContainsErrorSymbols);
        }
    }

    private static String getFieldsContainsErrorSymbols(Student student) {
        StringBuilder errorFields = new StringBuilder();
        boolean wasField = false;
        if (isNotBlank(student.getFirstName()) && !ALL_RUSSIAN_LETTERS.matcher(student.getFirstName()).matches()) {
            errorFields.append(" " + FIRST_NAME_FIELD);
            wasField = true;
        }
        if (isNotBlank(student.getLastName()) && !ALL_RUSSIAN_LETTERS.matcher(student.getLastName()).matches()) {
            checkAndInsertComma(wasField, errorFields);
            errorFields.append(" " + LAST_NAME_FIELD);
            wasField = true;
        }
        return errorFields.toString();
    }

    private static String getRequiredFieldsEmpty(Student student) {
        StringBuilder errorFields = new StringBuilder();
        boolean wasField = false;
        if (isBlank(student.getFirstName())) {
            errorFields.append(" " + FIRST_NAME_FIELD);
            wasField = true;
        }
        if (isBlank(student.getLastName())) {
            checkAndInsertComma(wasField, errorFields);
            errorFields.append(" " + LAST_NAME_FIELD);
            wasField = true;
        }
        return errorFields.toString();

    }

    private static void checkAndInsertComma(boolean wasField, StringBuilder stringBuilder) {
        if (wasField) {
            stringBuilder.append(COMMA);
        }
    }
}
