package com.example.dpma.service;

import com.example.dpma.dao.*;
import com.example.dpma.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    ProfessorDAO professorDAO;

    @Autowired
    SubjectService subjectService;

    @Autowired
    SubjectDAO subjectDAO;

    @Autowired
    ThesisDAO thesisDAO;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    ApplicationDAO applicationDAO;


    @Override
    public void saveProfile(Professor professor) {
        professorDAO.save(professor);

    }

    @Override
    public Professor findProfessorByUserId(int userId) {

        return professorDAO.findByUserId(userId);
    }

    @Override
    public void saveSubject(Professor professor, Subject subject) {

        professor.addSubject(subject);


    }

    @Override
    public List<Subject> listProfessorSubjects(Professor professor) {

        return professor.getSubjects();
    }

    @Override
    public List<Application> listApplications(Integer subjectId, Professor professor) {

        return subjectService.findById(subjectId).getApplications();
    }




    @Override
    public Student assignSubject(Professor professor, Integer subjectId,String strategy){
        Subject subject = subjectDAO.findById(subjectId).get();
        List<Application> applications = subject.getApplications();
        Student bestStudent = subject.findBestApplicant(strategy, applications);
        if(bestStudent==null){
            return null;
        }

        Thesis thesis = new Thesis();
        thesis.setProfessor(professor);
        thesis.setSubject(subject);
        thesis.setStudent(bestStudent);
        thesisDAO.save(thesis);
        professor.addThesis(thesis);


        applicationDAO.deleteByStudentId(bestStudent.getId());

        return bestStudent;
    }



    @Override
    public void assignSubjectToParticular(Professor professor, Integer subjectId, Integer studentId){
        Thesis thesis = new Thesis();
        thesis.setProfessor(professor);
        thesis.setSubject(subjectDAO.findById(subjectId).get());
        thesis.setStudent(studentDAO.findById(studentId).get());
        thesisDAO.save(thesis);
        professor.addThesis(thesis);


        applicationDAO.deleteByStudentId(studentId);
    }




}