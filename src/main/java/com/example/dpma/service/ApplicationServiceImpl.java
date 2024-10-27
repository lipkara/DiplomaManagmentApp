package com.example.dpma.service;

import com.example.dpma.dao.ApplicationDAO;
import com.example.dpma.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService{


    @Autowired
    ApplicationDAO applicationDAO;

    @Override
    public boolean isApplicationPresent(Integer student_id, Integer subject_id) {

        Optional<Application> storedApplication = applicationDAO.findApplicationByStudentIdAndSubjectId(student_id, subject_id);

        return storedApplication.isPresent();
    }
}