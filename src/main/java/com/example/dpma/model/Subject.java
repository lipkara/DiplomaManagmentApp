package com.example.dpma.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private int subject_id;

    @Column(name = "subject_name")
    private String subject_name;

    @ManyToOne
    @JoinColumn(name = "supervisor", referencedColumnName = "professor_id")
    private Professor professor;

    @Column(name = "objective")
    private String objective;

    @Column(name = "semester")
    private int semester;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Application> applications;




    //

    public int getId() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }


    public void setApplications(List<Application> application) {
        this.applications = application;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void addApplication(Application application) {
        applications.add(application);
    }

    public BestApplicantStrategy bestApplicantStrategy(String string){
        BestApplicantStrategyFactory bestApplicantStrategyFactory = new BestApplicantStrategyFactory();
        BestApplicantStrategy bestApplicantStrategy = bestApplicantStrategyFactory.createStrategy(string);
        return bestApplicantStrategy;
    }

    public Student findBestApplicant(String string, List<Application> applications){

        Student bestStudent = bestApplicantStrategy(string).findBestApplicant(applications);
        return bestStudent;
    }
}