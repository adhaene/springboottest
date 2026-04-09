package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.model.Tutorial;
import com.bezkoder.spring.jpa.h2.service.TutorialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TutorialControllerTest {

    @Mock
    private TutorialService tutorialService;

    @InjectMocks
    private TutorialController tutorialController;

    private Tutorial tutorial;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tutorial = new Tutorial("Spring Boot", "A Spring Boot tutorial", false);
    }

    // -------------------------------------------------------------------------
    // GET /tutorials
    // -------------------------------------------------------------------------

    @Test
    void getAllTutorials_noTitleParam_returnsList() {
        when(tutorialService.findAll()).thenReturn(Arrays.asList(tutorial));

        ResponseEntity<List<Tutorial>> response = tutorialController.getAllTutorials(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getAllTutorials_withTitleParam_returnsFilteredList() {
        when(tutorialService.findByTitleContaining("Spring")).thenReturn(Arrays.asList(tutorial));

        ResponseEntity<List<Tutorial>> response = tutorialController.getAllTutorials("Spring");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getAllTutorials_emptyResult_returnsNoContent() {
        when(tutorialService.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Tutorial>> response = tutorialController.getAllTutorials(null);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getAllTutorials_serviceThrows_returnsInternalServerError() {
        when(tutorialService.findAll()).thenThrow(new RuntimeException("DB error"));

        ResponseEntity<List<Tutorial>> response = tutorialController.getAllTutorials(null);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // -------------------------------------------------------------------------
    // GET /tutorials/{id}
    // -------------------------------------------------------------------------

    @Test
    void getTutorialById_found_returnsTutorial() {
        when(tutorialService.findById(1L)).thenReturn(Optional.of(tutorial));

        ResponseEntity<Tutorial> response = tutorialController.getTutorialById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tutorial, response.getBody());
    }

    @Test
    void getTutorialById_notFound_returnsNotFound() {
        when(tutorialService.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Tutorial> response = tutorialController.getTutorialById(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // -------------------------------------------------------------------------
    // POST /tutorials
    // -------------------------------------------------------------------------

    @Test
    void createTutorial_validInput_returnsCreated() {
        Tutorial saved = new Tutorial("Spring Boot", "A Spring Boot tutorial", false);
        when(tutorialService.save(any(Tutorial.class))).thenReturn(saved);

        ResponseEntity<Tutorial> response = tutorialController.createTutorial(tutorial);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void createTutorial_titleIsJava_throwsCustomException() {
        Tutorial javaTutorial = new Tutorial("java", "Java tutorial", false);
        when(tutorialService.save(any(Tutorial.class))).thenReturn(javaTutorial);

        // The controller catches the CustomException internally and returns 500,
        // because the catch block is generic (catches Exception).
        ResponseEntity<Tutorial> response = tutorialController.createTutorial(javaTutorial);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void createTutorial_serviceThrows_returnsInternalServerError() {
        when(tutorialService.save(any(Tutorial.class))).thenThrow(new RuntimeException("DB error"));

        ResponseEntity<Tutorial> response = tutorialController.createTutorial(tutorial);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // -------------------------------------------------------------------------
    // PUT /tutorials/{id}
    // -------------------------------------------------------------------------

    @Test
    void updateTutorial_found_returnsUpdatedTutorial() {
        Tutorial updated = new Tutorial("Updated Title", "Updated Desc", true);
        when(tutorialService.findById(1L)).thenReturn(Optional.of(tutorial));
        when(tutorialService.save(any(Tutorial.class))).thenReturn(updated);

        ResponseEntity<Tutorial> response = tutorialController.updateTutorial(1L, updated);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updated, response.getBody());
    }

    @Test
    void updateTutorial_notFound_returnsNotFound() {
        when(tutorialService.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Tutorial> response = tutorialController.updateTutorial(99L, tutorial);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // -------------------------------------------------------------------------
    // DELETE /tutorials/{id}
    // -------------------------------------------------------------------------

    @Test
    void deleteTutorial_success_returnsNoContent() {
        doNothing().when(tutorialService).deleteById(1L);

        ResponseEntity<HttpStatus> response = tutorialController.deleteTutorial(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(tutorialService, times(1)).deleteById(1L);
    }

    @Test
    void deleteTutorial_serviceThrows_returnsInternalServerError() {
        doThrow(new RuntimeException("DB error")).when(tutorialService).deleteById(1L);

        ResponseEntity<HttpStatus> response = tutorialController.deleteTutorial(1L);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // -------------------------------------------------------------------------
    // DELETE /tutorials
    // -------------------------------------------------------------------------

    @Test
    void deleteAllTutorials_success_returnsNoContent() {
        doNothing().when(tutorialService).deleteAll();

        ResponseEntity<HttpStatus> response = tutorialController.deleteAllTutorials();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(tutorialService, times(1)).deleteAll();
    }

    @Test
    void deleteAllTutorials_serviceThrows_returnsInternalServerError() {
        doThrow(new RuntimeException("DB error")).when(tutorialService).deleteAll();

        ResponseEntity<HttpStatus> response = tutorialController.deleteAllTutorials();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // -------------------------------------------------------------------------
    // GET /tutorials/published
    // -------------------------------------------------------------------------

    @Test
    void findByPublished_hasTutorials_returnsList() {
        Tutorial published = new Tutorial("Published", "Desc", true);
        when(tutorialService.findByPublished(true)).thenReturn(Arrays.asList(published));

        ResponseEntity<List<Tutorial>> response = tutorialController.findByPublished();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void findByPublished_empty_returnsNoContent() {
        when(tutorialService.findByPublished(true)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Tutorial>> response = tutorialController.findByPublished();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void findByPublished_serviceThrows_returnsInternalServerError() {
        when(tutorialService.findByPublished(true)).thenThrow(new RuntimeException("DB error"));

        ResponseEntity<List<Tutorial>> response = tutorialController.findByPublished();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}