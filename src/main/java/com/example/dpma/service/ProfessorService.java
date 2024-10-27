package com.example.dpma.service;

import com.example.dpma.model.Application;
import com.example.dpma.model.Professor;
import com.example.dpma.model.Student;
import com.example.dpma.model.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfessorService {

    public void saveProfile(Professor professor);

    public Professor findProfessorByUserId(int userId);

    public void saveSubject(Professor professor, Subject subject);

    public List<Subject> listProfessorSubjects(Professor professor);

    public List<Application> listApplications(Integer subjectId, Professor professor);


    public Student assignSubject(Professor professor, Integer subjectId,String strategy);

    public void assignSubjectToParticular(Professor professor, Integer subjectId, Integer studentId);

    //public void deleteSubject(Professor professor,Integer subjectId);
}