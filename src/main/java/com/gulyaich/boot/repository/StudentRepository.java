package com.gulyaich.boot.repository;

import com.gulyaich.boot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
