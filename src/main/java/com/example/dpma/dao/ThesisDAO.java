package com.example.dpma.dao;

import com.example.dpma.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThesisDAO extends JpaRepository<Thesis, Integer> {



    public Optional<Thesis> findThesisByStudentId(Integer student_id);

}