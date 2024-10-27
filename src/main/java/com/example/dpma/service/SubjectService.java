package com.example.dpma.service;


import com.example.dpma.model.Subject;
import org.springframework.stereotype.Service;

@Service
public interface SubjectService {

    public void saveSubject(Subject subject);

    public Subject findById(Integer subjectId);

    public void deleteById(Integer subjectId);

}
