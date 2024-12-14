package com.studentapi.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.studentapi.server.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{

}


