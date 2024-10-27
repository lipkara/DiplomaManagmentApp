package com.example.dpma.dao;

import com.example.dpma.model.Student;
import com.example.dpma.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentDAO extends JpaRepository<Student, Integer> {

    Student findByUserId(int userId);




}
