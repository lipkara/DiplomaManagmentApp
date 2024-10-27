package com.example.dpma.dao;

import com.example.dpma.model.Professor;
import com.example.dpma.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectDAO extends JpaRepository<Subject, Integer> {

    Optional<Subject> findById(int subject_id);


}