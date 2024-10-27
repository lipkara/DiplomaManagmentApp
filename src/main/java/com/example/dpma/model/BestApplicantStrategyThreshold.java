package com.example.dpma.model;

import java.util.List;

public class BestApplicantStrategyThreshold implements BestApplicantStrategy{



    public BestApplicantStrategyThreshold(){}

    @Override
    public Student findBestApplicant(List<Application> applications) {
        for(int i=0; i<applications.size(); i++){
            if(applications.get(i).getStudent().getCurr_average_grade()>=5 && applications.get(i).getStudent().getNum_of_remaining_courses()<=5){
                return applications.get(i).getStudent();
            }
        }
        return null;
    }
}