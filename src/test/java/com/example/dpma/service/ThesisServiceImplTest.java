package com.example.dpma.service;

import com.example.dpma.dao.ThesisDAO;
import com.example.dpma.model.Thesis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ThesisServiceImplTest {

    @Mock
    private ThesisDAO thesisDAO;

    @InjectMocks
    private ThesisServiceImpl thesisService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveThesis() {
        Thesis thesis = new Thesis();
        thesisService.saveThesis(thesis);
        verify(thesisDAO, times(1)).save(thesis);
    }

    @Test
    void testIsThesisPresent_WhenThesisExists() {
        Integer studentId = 1;
        Thesis thesis = new Thesis();
        when(thesisDAO.findThesisByStudentId(studentId)).thenReturn(Optional.of(thesis));

        boolean result = thesisService.isThesisPresent(studentId);

        assertTrue(result);
        verify(thesisDAO, times(1)).findThesisByStudentId(studentId);
    }

    @Test
    void testIsThesisPresent_WhenThesisDoesNotExist() {
        Integer studentId = 1;
        when(thesisDAO.findThesisByStudentId(studentId)).thenReturn(Optional.empty());

        boolean result = thesisService.isThesisPresent(studentId);

        assertFalse(result);
        verify(thesisDAO, times(1)).findThesisByStudentId(studentId);
    }

    @Test
    void testFindById() {
        Integer thesisId = 1;
        Thesis thesis = new Thesis();
        when(thesisDAO.findById(thesisId)).thenReturn(Optional.of(thesis));

        Thesis result = thesisService.findById(thesisId);

        assertEquals(thesis, result);
        verify(thesisDAO, times(1)).findById(thesisId);
    }

    @Test
    void testSetThesisGrade() {
        Thesis thesis = new Thesis();
        thesis.setGrade_impl(9);
        thesis.setGrade_rep(8);
        thesis.setGrade_pres(7);
        thesis.setGrade_total(0);

        thesisService.setThesisGrade(thesis);

        assertEquals(8.55, thesis.getGrade_total());
        verify(thesisDAO, times(1)).save(thesis);
    }
}
