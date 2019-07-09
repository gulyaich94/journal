package com.gulyaich.boot.repository;

import com.gulyaich.boot.entity.Student;
import com.mifmif.common.regex.Generex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class StudentRepositoryTest {

    private static final Generex TWO_RUSSIAN_LETTERS = new Generex("[а-яА-Я-]{2}");
    private static final Generex NAME_TO_UPDATE = new Generex("[а-яА-Я-]{10}");

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    private List<Student> createStudents() {
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.setFirstName(TWO_RUSSIAN_LETTERS.random());
            student.setLastName(TWO_RUSSIAN_LETTERS.random());
            entityManager.persist(student);
            entityManager.flush();
            studentList.add(student);
        }
        return studentList;
    }

    @Test
    public void testGetStudents() {
        List<Student> studentList = createStudents();
        List<Student> result = studentRepository.findAll();
        assertThat(result.size(), is(5));
        assertEquals(result, studentList);
    }

    @Test
    public void testFindById() {
        List<Student> studentList = createStudents();
        Student result = studentRepository.findById(studentList.get(0).getId()).get();
        assertThat(result, samePropertyValuesAs(studentList.get(0)));
    }

    @Test
    public void testCreateStudent() {
        Student student = new Student();
        student.setFirstName(TWO_RUSSIAN_LETTERS.random());
        student.setLastName(TWO_RUSSIAN_LETTERS.random());

        Student result = studentRepository.save(student);
        assertThat(result, samePropertyValuesAs(student));
    }

    @Test
    public void testDeleteStudent() {
        List<Student> studentList = createStudents();
        Student studentToDelete = studentList.get(0);
        studentRepository.deleteById(studentToDelete.getId());
        List<Student> resultList = studentRepository.findAll();

        assertThat(resultList.size(), is(4));
        for (Student student : resultList) {
            assertNotEquals(student, studentToDelete);
        }
    }

    @Test
    public void testUpdateStudent() {
        Student studentToUpdate = createStudents().get(0);
        studentToUpdate.setFirstName(NAME_TO_UPDATE.random());
        Student result = studentRepository.save(studentToUpdate);
        assertThat(result, samePropertyValuesAs(studentToUpdate));
    }
}
