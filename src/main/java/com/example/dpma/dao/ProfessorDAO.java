package com.example.dpma.dao;

import com.example.dpma.model.Professor;
import com.example.dpma.model.Student;
import com.example.dpma.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorDAO extends JpaRepository<Professor, Integer> {

    Professor findByUserId(int userId);


}
