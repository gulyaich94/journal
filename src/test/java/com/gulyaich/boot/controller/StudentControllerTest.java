package com.gulyaich.boot.controller;

import com.gulyaich.boot.entity.Student;
import com.gulyaich.boot.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private StudentService studentService;

    @Before
    public void init() {
        Student student = new Student();
        student.setFirstName("Иван");
        student.setLastName("Иванов");
        when(studentService.findById(1L)).thenReturn(Optional.of(student));
        when(studentService.createStudent(any())).thenReturn(student);
    }

    @Test
    public void testGetStudents() throws Exception {
        ResponseEntity<List> response = restTemplate.getForEntity(
                "/api/student/",
                List.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void testCreateStudent() throws Exception {
        ResponseEntity<Student> response = restTemplate.postForEntity(
                "/api/student/create",
                new Student(),
                Student.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());

        verify(studentService, times(1)).createStudent(any());
    }


    @Test
    public void testFindById() {
        ResponseEntity<Student> response = restTemplate.getForEntity(
                "/api/student/detail/" + 1L,
                Student.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());

        verify(studentService, times(1)).findById(1L);
    }

}
