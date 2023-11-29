package br.edu.ifgoias.academico.resources;

import br.edu.ifgoias.academico.entities.Curso;
import br.edu.ifgoias.academico.services.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CursoResourceTest {

    @Mock
    private CursoService cursoService;

    @InjectMocks
    private CursoResource cursoResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso(1, "Curso 1"));
        cursos.add(new Curso(2, "Curso 2"));

        when(cursoService.findAll()).thenReturn(cursos);

        ResponseEntity<List<Curso>> response = cursoResource.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cursos, response.getBody());

        verify(cursoService, times(1)).findAll();
        verifyNoMoreInteractions(cursoService);
    }

    @Test
    void testFindById() {
        int id = 1;
        Curso curso = new Curso(id, "Curso 1");

        when(cursoService.findById(id)).thenReturn(curso);

        ResponseEntity<Curso> response = cursoResource.findById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(curso, response.getBody());

        verify(cursoService, times(1)).findById(id);
        verifyNoMoreInteractions(cursoService);
    }

    @Test
    void testInsert() {
        Curso curso = new Curso(1, "Curso 1");

        when(cursoService.insert(curso)).thenReturn(curso);

        ResponseEntity<Curso> response = cursoResource.insert(curso);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(curso, response.getBody());

        verify(cursoService, times(1)).insert(curso);
        verifyNoMoreInteractions(cursoService);
    }

    @Test
    void testDelete() {
        int id = 1;

        ResponseEntity<Void> response = cursoResource.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(cursoService, times(1)).delete(id);
        verifyNoMoreInteractions(cursoService);
    }

    @Test
    void testUpdate() {
        int id = 1;
        Curso curso = new Curso(id, "Curso 1");

        when(cursoService.update(id, curso)).thenReturn(curso);

        ResponseEntity<Curso> response = cursoResource.update(id, curso);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(curso, response.getBody());

        verify(cursoService, times(1)).update(id, curso);
        verifyNoMoreInteractions(cursoService);
    }
}