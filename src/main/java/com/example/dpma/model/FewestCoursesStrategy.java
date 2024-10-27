package com.example.dpma.model;

public class FewestCoursesStrategy extends TemplateStrategyAlgorithm{

    public FewestCoursesStrategy(){}


    @Override
    public int compareApplications(Application fapplication, Application sapplication) {
        if (fapplication.getStudent().getNum_of_remaining_courses() < sapplication.getStudent().getNum_of_remaining_courses()){
            return fapplication.getApplication_id();
        }
        else if(fapplication.getStudent().getNum_of_remaining_courses() > sapplication.getStudent().getNum_of_remaining_courses()){
            return sapplication.getApplication_id();
        }
        else
            return fapplication.getApplication_id();
    }

}