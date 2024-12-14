package com.studentapi.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentapi.server.model.Student;
import com.studentapi.server.repo.StudentRepo;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudents() {
        
        return studentRepo.findAll();
    }

   public Student addStudent(Student student) {

        return studentRepo.save(student);
   }
   
}
