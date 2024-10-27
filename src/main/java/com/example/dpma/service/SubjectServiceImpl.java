package com.example.dpma.service;


import com.example.dpma.dao.ApplicationDAO;
import com.example.dpma.dao.SubjectDAO;
import com.example.dpma.model.Application;
import com.example.dpma.model.Subject;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectDAO subjectDAO;
    @Autowired
    ApplicationDAO applicationDAO;

    @Override
    public void saveSubject(Subject subject) {
        subjectDAO.save(subject);

    }

    @Override
    public Subject findById(Integer subjectId) {


        return subjectDAO.findById(subjectId).get();

    }

    @Override
    @Transactional
    public void deleteById(Integer subjectId) {
        System.out.println(subjectId);

        Subject subject = subjectDAO.findById(subjectId).get();
        List<Application> applications = subject.getApplications();
        for (Application application : applications) {
            applicationDAO.delete(application);
        }

        subjectDAO.deleteById(subjectId);
    }


}