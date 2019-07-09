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
    private static int MIN_LENGTH = 2;

    public static void checkFields(Student student) {
        emptyRequiredCheck(student);
        symbolsCheck(student);
    }

    private static void symbolsCheck(Student student) {
        String fieldsContainsErrorSymbols = getFieldsContainsErrorSymbols(student);
        if (isNotBlank(fieldsContainsErrorSymbols)) {
            throw new StudentErrorSymbolsException(fieldsContainsErrorSymbols);
        }
    }

    private static void emptyRequiredCheck(Student student) {
        String emptyRequiredFields = getRequiredFieldsEmpty(student);
        if (isNotBlank(emptyRequiredFields)) {
            throw new StudentEmptyFieldsException(emptyRequiredFields);
        }
    }

    private static String getFieldsContainsErrorSymbols(Student student) {
        StringBuilder errorFields = new StringBuilder();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        boolean wasField = false;
        if (isNotBlank(firstName)
                && (!ALL_RUSSIAN_LETTERS.matcher(firstName).matches() || firstName.length() < MIN_LENGTH )) {
            errorFields.append(" " + FIRST_NAME_FIELD);
            wasField = true;
        }
        if (isNotBlank(lastName)
                && (!ALL_RUSSIAN_LETTERS.matcher(lastName).matches() || lastName.length() < MIN_LENGTH)) {
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
