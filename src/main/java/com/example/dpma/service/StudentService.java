package com.example.dpma.service;

import com.example.dpma.model.Student;
import com.example.dpma.model.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    public void saveProfile(Student student);

    public Student findStudentByUserId(int userId);

    public List<Subject> listStudentSubjects();

    public void applyToSubject(Student student,Integer subjectId);

    public Student findStudentById(int student_id);


}