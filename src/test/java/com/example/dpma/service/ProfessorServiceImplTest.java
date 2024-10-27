package com.example.dpma.service;

import com.example.dpma.dao.*;
import com.example.dpma.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProfessorServiceImplTest {

    @Mock
    private ProfessorDAO professorDAO;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private ProfessorServiceImpl professorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveProfile() {
        Professor professor = new Professor();
        professorService.saveProfile(professor);
        verify(professorDAO, times(1)).save(professor);
    }

    @Test
    void testFindProfessorByUserId() {
        int userId = 1;
        Professor expectedProfessor = new Professor();
        when(professorDAO.findByUserId(userId)).thenReturn(expectedProfessor);

        Professor result = professorService.findProfessorByUserId(userId);

        assertEquals(expectedProfessor, result);
        verify(professorDAO, times(1)).findByUserId(userId);
    }

    @Test
    void testListProfessorSubjects() {
        Professor professor = new Professor();
        List<Subject> expectedSubjects = new ArrayList<>();
        professor.setSubjects(expectedSubjects);

        List<Subject> result = professorService.listProfessorSubjects(professor);

        assertEquals(expectedSubjects, result);
    }

    @Test
    void testListApplications() {
        Integer subjectId = 1;
        Professor professor = new Professor();
        Subject subject = new Subject();
        List<Application> expectedApplications = new ArrayList<>();
        subject.setApplications(expectedApplications);

        when(subjectService.findById(subjectId)).thenReturn(subject);

        List<Application> result = professorService.listApplications(subjectId, professor);

        assertEquals(expectedApplications, result);
        verify(subjectService, times(1)).findById(subjectId);
    }






}