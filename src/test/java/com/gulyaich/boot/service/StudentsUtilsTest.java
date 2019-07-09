package com.gulyaich.boot.service;

import com.gulyaich.boot.entity.Student;
import com.gulyaich.boot.exception.StudentEmptyFieldsException;
import com.gulyaich.boot.exception.StudentErrorSymbolsException;
import com.mifmif.common.regex.Generex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class StudentsUtilsTest {

    private static final Generex ONE_RUSSIAN_LETTER = new Generex("[а-яА-Я-]");
    private static final Generex TWO_RUSSIAN_LETTERS = new Generex("[а-яА-Я-]{2}");
    private static final Generex TWO_NUMBERS = new Generex("[0-9]{2}");
    private static final Generex EXCLUDE_RUSSIAN = new Generex("[a-zA-Z0-9#$@&*]{5}");

    @Test(expected = StudentEmptyFieldsException.class)
    public void testEmptyFieldBoth() {
        Student invalidStudent = new Student();
        StudentUtils.checkFields(invalidStudent);
    }

    @Test(expected = StudentEmptyFieldsException.class)
    public void testEmptyFieldFirst() {
        Student invalidStudent = new Student();
        invalidStudent.setLastName(TWO_RUSSIAN_LETTERS.random());
        StudentUtils.checkFields(invalidStudent);
    }

    @Test(expected = StudentEmptyFieldsException.class)
    public void testEmptyFieldSecond() {
        Student invalidStudent = new Student();
        invalidStudent.setFirstName(TWO_RUSSIAN_LETTERS.random());
        StudentUtils.checkFields(invalidStudent);
    }

    @Test(expected = StudentErrorSymbolsException.class)
    public void testInvalidSymbolsTooLittleFirst() {
        Student invalidStudent = new Student();
        invalidStudent.setFirstName(ONE_RUSSIAN_LETTER.random());
        invalidStudent.setLastName(TWO_RUSSIAN_LETTERS.random());
        StudentUtils.checkFields(invalidStudent);
    }

    @Test(expected = StudentErrorSymbolsException.class)
    public void testInvalidSymbolsTooLittleSecond() {
        Student invalidStudent = new Student();
        invalidStudent.setFirstName(TWO_RUSSIAN_LETTERS.random());
        invalidStudent.setLastName(ONE_RUSSIAN_LETTER.random());
        StudentUtils.checkFields(invalidStudent);
    }

    @Test(expected = StudentErrorSymbolsException.class)
    public void testInvalidSymbolsNumbersFirst() {
        Student invalidStudent = new Student();
        invalidStudent.setFirstName(TWO_NUMBERS.random());
        invalidStudent.setLastName(TWO_RUSSIAN_LETTERS.random());
        StudentUtils.checkFields(invalidStudent);
    }

    @Test(expected = StudentErrorSymbolsException.class)
    public void testInvalidSymbolsNumbersSecond() {
        Student invalidStudent = new Student();
        invalidStudent.setFirstName(TWO_RUSSIAN_LETTERS.random());
        invalidStudent.setLastName(TWO_NUMBERS.random());
        StudentUtils.checkFields(invalidStudent);
    }

    @Test(expected = StudentErrorSymbolsException.class)
    public void testInvalidSymbolsExcludeCyrillicFirst() {
        Student invalidStudent = new Student();
        invalidStudent.setFirstName(EXCLUDE_RUSSIAN.random());
        invalidStudent.setLastName(TWO_RUSSIAN_LETTERS.random());
        StudentUtils.checkFields(invalidStudent);
    }

    @Test(expected = StudentErrorSymbolsException.class)
    public void testInvalidSymbolsExcludeCyrillicSecond() {
        Student invalidStudent = new Student();
        invalidStudent.setFirstName(TWO_RUSSIAN_LETTERS.random());
        invalidStudent.setLastName(EXCLUDE_RUSSIAN.random());
        StudentUtils.checkFields(invalidStudent);
    }
}
