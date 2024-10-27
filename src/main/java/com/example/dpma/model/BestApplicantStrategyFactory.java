package com.example.dpma.model;

public class BestApplicantStrategyFactory {

    public BestApplicantStrategyFactory(){}

    public BestApplicantStrategy createStrategy(String string) {
        if (string.equals("random")) {
            BestApplicantStrategy strategy = new RandomApplicantStrategy();
            return strategy;

        } else if (string.equals("bestAvg")) {
            BestApplicantStrategy strategy = new BestAvgGradeStrategy();
            return strategy;

        } else if (string.equals("fewestRemaining")) {
            BestApplicantStrategy strategy = new FewestCoursesStrategy();
            return strategy;

        } else if (string.equals("aboveThresh")) {
            BestApplicantStrategy strategy = new BestApplicantStrategyThreshold();
            return strategy;

        }
        return null;

    }


}