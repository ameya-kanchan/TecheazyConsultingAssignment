package com.studentapi.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentapi.server.model.Subject;
import com.studentapi.server.repo.SubjectRepo;

@Service
public class SubjectService {

    @Autowired
    SubjectRepo subjectRepo;

    public Subject addSubject(Subject subject) {

        return subjectRepo.save(subject);
    }

    public List<Subject> getAllSubjects() {

        return subjectRepo.findAll();
    }
 
}
