package com.example.dpma.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int student_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    ///////////////////////
    @Column(name = "full_name")
    private String full_name;

    @Column(name = "year_of_studies")
    private int year_of_studies;

    @Column(name = "curr_average_grade")
    private float curr_average_grade;

    @Column(name = "num_of_remaining_courses")
    private int num_of_remaining_courses;

    @OneToMany(mappedBy = "student")
    private List<Application> applications;

    public Student(int student_id, String full_name, int year_of_studies, float curr_average_grade, int num_of_remaining_courses) {
        this.student_id = student_id;
        this.full_name = full_name;
        this.year_of_studies = year_of_studies;
        this.curr_average_grade = curr_average_grade;
        this.num_of_remaining_courses = num_of_remaining_courses;
    }

    public Student() {

    }

    public int getId() {
        return student_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFull_name() {
        return full_name;
    }

    public int getYear_of_studies() {
        return year_of_studies;
    }

    public float getCurr_average_grade() {
        return curr_average_grade;
    }

    public int getNum_of_remaining_courses() {
        return num_of_remaining_courses;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

//Setters

    public void setId(int id) {
        this.student_id = id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setYear_of_studies(int year_of_studies) {
        this.year_of_studies = year_of_studies;
    }

    public void setCurr_average_grade(float curr_average_grade) {
        this.curr_average_grade = curr_average_grade;
    }

    public void setNum_of_remaining_courses(int num_of_remaining_courses) {
        this.num_of_remaining_courses = num_of_remaining_courses;
    }

    public void addApplication(Application application){
        applications.add(application);
    }


    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", user=" + user +
                ", full_name='" + full_name + '\'' +
                ", year_of_studies=" + year_of_studies +
                ", curr_average_grade=" + curr_average_grade +
                ", num_of_remaining_courses=" + num_of_remaining_courses +
                '}';
    }
}