package com.example.dpma.service;

import com.example.dpma.dao.ApplicationDAO;
import com.example.dpma.dao.StudentDAO;
import com.example.dpma.dao.SubjectDAO;
import com.example.dpma.model.Application;
import com.example.dpma.model.Student;
import com.example.dpma.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private SubjectDAO subjectDAO;

    @Autowired
    private ApplicationDAO applicationDAO;

    @Override
    public void saveProfile(Student student) {

        studentDAO.save(student);
    }

    @Override
    public Student findStudentByUserId(int userId) {
        return studentDAO.findByUserId(userId);
    }

    @Override
    public List<Subject> listStudentSubjects() {
        return subjectDAO.findAll();
    }

    @Override
    public void applyToSubject(Student student, Integer subjectId) {

        Application application = new Application();
        Optional<Subject> subject = subjectDAO.findById(subjectId);
        application.setStudent(student);
        application.setSubject(subject.get());
        subject.get().addApplication(application);
        applicationDAO.save(application);
        student.addApplication(application);


    }
    @Override
    public Student findStudentById(int student_id){

        return studentDAO.findById(student_id).get();
    }
}

