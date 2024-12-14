package com.studentapi.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentapi.server.model.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Integer>{

}
