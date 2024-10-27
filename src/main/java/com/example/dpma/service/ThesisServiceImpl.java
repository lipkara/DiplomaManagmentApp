package com.example.dpma.service;

import com.example.dpma.dao.ThesisDAO;
import com.example.dpma.model.Thesis;
import com.sun.jdi.FloatValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThesisServiceImpl implements ThesisService{

    @Autowired
    ThesisDAO thesisDAO;


    @Override
    public void saveThesis(Thesis thesis) {
        thesisDAO.save(thesis);
    }

    @Override
    public boolean isThesisPresent(Integer student_id){
        Optional<Thesis> storedThesis = thesisDAO.findThesisByStudentId(student_id);
        return storedThesis.isPresent();
    }

    @Override
    public Thesis findById(Integer thesisId){
        return thesisDAO.findById(thesisId).get();
    }

    @Override
    public void setThesisGrade(Thesis thesis){
        thesis.setGrade_total(0.7*thesis.getGrade_impl() + 0.15*thesis.getGrade_rep()+0.15*thesis.getGrade_pres());
        thesisDAO.save(thesis);

    }


}