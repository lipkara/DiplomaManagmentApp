package com.example.dpma.model;
import jakarta.persistence.*;


@Entity
@Table(name = "thesis")
public class Thesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "thesis_id")
    private int thesis_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    private Subject subject;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supervisor", referencedColumnName = "professor_id")
    private Professor professor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    @Column(name = "grade_impl")
    private double grade_impl;

    @Column(name = "grade_rep")
    private double grade_rep;

    @Column(name = "grade_pres")
    private double grade_pres;

    @Column(name = "grade_total")
    private double grade_total;

    //
    public int getThesis_id() {
        return thesis_id;
    }

    public void setThesis_id(int thesis_id) {
        this.thesis_id = thesis_id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getGrade_impl() {
        return grade_impl;
    }

    public void setGrade_impl(double grade_impl) {
        this.grade_impl = grade_impl;
    }

    public double getGrade_rep() {
        return grade_rep;
    }

    public void setGrade_rep(double grade_rep) {
        this.grade_rep = grade_rep;
    }

    public double getGrade_pres() {
        return grade_pres;
    }

    public void setGrade_pres(double grade_pres) {
        this.grade_pres = grade_pres;
    }

    public double getGrade_total() {
        return grade_total;
    }

    public void setGrade_total(double grade_total) {
        this.grade_total = grade_total;
    }
}