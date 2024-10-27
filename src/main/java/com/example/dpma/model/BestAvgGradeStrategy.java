package com.example.dpma.model;

public class BestAvgGradeStrategy extends TemplateStrategyAlgorithm{

    public BestAvgGradeStrategy(){}

    @Override
    public int compareApplications(Application fapplication, Application sapplication) {
        if (fapplication.getStudent().getCurr_average_grade() > sapplication.getStudent().getCurr_average_grade()){

            return fapplication.getApplication_id();
        }
        else if (fapplication.getStudent().getCurr_average_grade() < sapplication.getStudent().getCurr_average_grade())
            return sapplication.getApplication_id();
        else
            return fapplication.getApplication_id();
    }

}