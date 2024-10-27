package com.example.dpma.model;

import java.util.List;

public interface BestApplicantStrategy {

    public Student findBestApplicant(List<Application> applications);

}