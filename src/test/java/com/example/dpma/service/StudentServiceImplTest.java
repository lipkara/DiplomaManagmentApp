package com.example.dpma.service;
import com.example.dpma.dao.ApplicationDAO;
import com.example.dpma.dao.StudentDAO;
import com.example.dpma.dao.SubjectDAO;
import com.example.dpma.model.Student;
import com.example.dpma.model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    @Mock
    private StudentDAO studentDAO;

    @Mock
    private SubjectDAO subjectDAO;

    @Mock
    private ApplicationDAO applicationDAO;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveProfile() {
        Student student = new Student();
        studentService.saveProfile(student);
        verify(studentDAO, times(1)).save(student);
    }

    @Test
    void testFindStudentByUserId() {
        int userId = 1;
        Student expectedStudent = new Student();
        when(studentDAO.findByUserId(userId)).thenReturn(expectedStudent);

        Student result = studentService.findStudentByUserId(userId);

        assertEquals(expectedStudent, result);
        verify(studentDAO, times(1)).findByUserId(userId);
    }

    @Test
    void testListStudentSubjects() {
        List<Subject> expectedSubjects = new ArrayList<>();
        when(subjectDAO.findAll()).thenReturn(expectedSubjects);

        List<Subject> result = studentService.listStudentSubjects();

        assertEquals(expectedSubjects, result);
        verify(subjectDAO, times(1)).findAll();
    }


    @Test
    void testFindStudentById() {
        int studentId = 1;
        Student expectedStudent = new Student();
        when(studentDAO.findById(studentId)).thenReturn(Optional.of(expectedStudent));

        Student result = studentService.findStudentById(studentId);

        assertEquals(expectedStudent, result);
        verify(studentDAO, times(1)).findById(studentId);
    }
}