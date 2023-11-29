package br.edu.ifgoias.academico.services;

import br.edu.ifgoias.academico.entities.Curso;
import br.edu.ifgoias.academico.repositories.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso(1, "Curso 1"));
        cursos.add(new Curso(2, "Curso 2"));

        when(cursoRepository.findAll()).thenReturn(cursos);

        List<Curso> result = cursoService.findAll();

        assertEquals(cursos, result);

        verify(cursoRepository, times(1)).findAll();
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void testFindById() {
        Curso curso = new Curso(1, "Curso 1");

        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));

        Curso result = cursoService.findById(1);

        assertEquals(curso, result);

        verify(cursoRepository, times(1)).findById(1);
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void testFindById_NotFound() {
        when(cursoRepository.findById(1)).thenReturn(Optional.empty());

        try {
            cursoService.findById(1);
        } catch (ResponseStatusException ex) {
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
        }

        verify(cursoRepository, times(1)).findById(1);
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void testInsert() {
        Curso curso = new Curso(1, "Curso 1");

        when(cursoRepository.save(curso)).thenReturn(curso);

        Curso result = cursoService.insert(curso);

        assertEquals(curso, result);

        verify(cursoRepository, times(1)).save(curso);
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void testDelete() {
        cursoService.delete(1);

        verify(cursoRepository, times(1)).deleteById(1);
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void testUpdate() {
        Curso curso = new Curso(1, "Curso 1");
        Curso cursoAlterado = new Curso(1, "Curso 1 Alterado");

        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));
        when(cursoRepository.save(curso)).thenReturn(curso);

        Curso result = cursoService.update(1, cursoAlterado);

        assertEquals(curso, result);
        assertEquals(cursoAlterado.getNomecurso(), result.getNomecurso());

        verify(cursoRepository, times(1)).findById(1);
        verify(cursoRepository, times(1)).save(curso);
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void testUpdate_NotFound() {
        Curso curso = new Curso(1, "Curso 1");

        when(cursoRepository.findById(1)).thenReturn(Optional.empty());

        try {
            cursoService.update(1, curso);
        } catch (ResponseStatusException ex) {
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
        }

        verify(cursoRepository, times(1)).findById(1);
        verifyNoMoreInteractions(cursoRepository);
    }
}