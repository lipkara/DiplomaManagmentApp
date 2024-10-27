package com.example.dpma.service;

import com.example.dpma.model.Application;
import org.springframework.stereotype.Service;

@Service
public interface ApplicationService {

    public boolean isApplicationPresent(Integer student_id, Integer subject_id);
}