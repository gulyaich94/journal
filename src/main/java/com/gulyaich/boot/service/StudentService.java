package com.gulyaich.boot.service;

import com.gulyaich.boot.entity.Student;
import com.gulyaich.boot.exception.EmptyFieldsException;
import com.gulyaich.boot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        checkRequiredFields(student);
        return studentRepository.save(student);
    }

    private void checkRequiredFields(Student student) {
        if (isBlank(student.getFirstName()) || isBlank(student.getLastName())) {
            throw new EmptyFieldsException(createErrorMessage(student));
        }
    }

    private String createErrorMessage(Student student) {
        StringBuilder errorMessage = new StringBuilder();
        if (isBlank(student.getFirstName())) {
            errorMessage.append(" имя");
        }
        if (isBlank(student.getLastName())) {
            errorMessage.append(" фамилия");
        }
        return errorMessage.toString();
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

}
