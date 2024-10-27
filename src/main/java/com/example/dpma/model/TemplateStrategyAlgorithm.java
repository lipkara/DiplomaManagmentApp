package com.example.dpma.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class TemplateStrategyAlgorithm implements BestApplicantStrategy{




    public TemplateStrategyAlgorithm(){

    }

    @Override
    public Student findBestApplicant(List<Application> applications){
        Application bestApplication = applications.get(0);
        int appID;

        for (int i =1; i < applications.size(); i++){
            appID = compareApplications(bestApplication, applications.get(i));
            if (applications.get(i).getApplication_id() == appID){
                bestApplication = applications.get(i);
            }

        }
        return bestApplication.getStudent();

    }
    public abstract int compareApplications(Application fapplication, Application sapplication);


}