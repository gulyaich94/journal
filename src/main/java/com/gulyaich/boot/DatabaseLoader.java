package com.gulyaich.boot;

import com.gulyaich.boot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... strings) throws Exception {
//        this.studentRepository.save(new Student("Frodo", "Baggins", "ring bearer"));
    }
}