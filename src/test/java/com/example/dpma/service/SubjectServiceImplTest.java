package com.example.dpma.service;
import com.example.dpma.dao.ApplicationDAO;
import com.example.dpma.dao.SubjectDAO;
import com.example.dpma.model.Application;
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

class SubjectServiceImplTest {

    @Mock
    private SubjectDAO subjectDAO;

    @Mock
    private ApplicationDAO applicationDAO;

    @InjectMocks
    private SubjectServiceImpl subjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveSubject() {
        Subject subject = new Subject();
        subjectService.saveSubject(subject);
        verify(subjectDAO, times(1)).save(subject);
    }

    @Test
    void testFindById() {
        Integer subjectId = 1;
        Subject expectedSubject = new Subject();
        when(subjectDAO.findById(subjectId)).thenReturn(Optional.of(expectedSubject));

        Subject result = subjectService.findById(subjectId);

        assertEquals(expectedSubject, result);
        verify(subjectDAO, times(1)).findById(subjectId);
    }

    @Test
    void testDeleteById() {
        Integer subjectId = 1;
        Subject subject = new Subject();
        List<Application> applications = new ArrayList<>();
        applications.add(new Application());
        subject.setApplications(applications);

        when(subjectDAO.findById(subjectId)).thenReturn(Optional.of(subject));

        subjectService.deleteById(subjectId);

        verify(applicationDAO, times(1)).delete(any(Application.class));
        verify(subjectDAO, times(1)).deleteById(subjectId);
    }
}