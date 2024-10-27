package com.example.dpma.model;

import java.util.Random;


public class RandomApplicantStrategy extends TemplateStrategyAlgorithm{

    public RandomApplicantStrategy(){}

    @Override
    public int compareApplications(Application fapplication, Application sapplication) {
        Random rand = new Random();
        int result = rand.nextInt(1,3);
        if (result == 1){
            return fapplication.getApplication_id();
        }
        return sapplication.getApplication_id();
    }
}