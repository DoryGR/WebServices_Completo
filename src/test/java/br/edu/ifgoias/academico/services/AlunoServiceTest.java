package br.edu.ifgoias.academico.services;

import br.edu.ifgoias.academico.entities.Aluno;
import br.edu.ifgoias.academico.repositories.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Aluno> alunos = new ArrayList<>();
        alunos.add(new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1)));
        alunos.add(new Aluno(2, "Maria", "Feminino", LocalDate.of(2001, 2, 2)));

        when(alunoRepository.findAll()).thenReturn(alunos);

        List<Aluno> result = alunoService.findAll();

        assertEquals(alunos, result);

        verify(alunoRepository, times(1)).findAll();
        verifyNoMoreInteractions(alunoRepository);
    }

    @Test
    void testFindById() {
        Aluno aluno = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));

        when(alunoRepository.findById(1)).thenReturn(Optional.of(aluno));

        Aluno result = alunoService.findById(1);

        assertEquals(aluno, result);

        verify(alunoRepository, times(1)).findById(1);
        verifyNoMoreInteractions(alunoRepository);
    }

    @Test
    void testFindById_NotFound() {
        when(alunoRepository.findById(1)).thenReturn(Optional.empty());

        try {
            alunoService.findById(1);
        } catch (ResponseStatusException ex) {
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
        }

        verify(alunoRepository, times(1)).findById(1);
        verifyNoMoreInteractions(alunoRepository);
    }

    @Test
    void testInsert() {
        Aluno aluno = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));

        when(alunoRepository.save(aluno)).thenReturn(aluno);

        Aluno result = alunoService.insert(aluno);

        assertEquals(aluno, result);

        verify(alunoRepository, times(1)).save(aluno);
        verifyNoMoreInteractions(alunoRepository);
    }

    @Test
    void testDelete() {
        ResponseEntity<Void> response = alunoService.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(alunoRepository, times(1)).deleteById(1);
        verifyNoMoreInteractions(alunoRepository);
    }

    @Test
    void testUpdate() {
        Aluno aluno = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));

        when(alunoRepository.findById(1)).thenReturn(Optional.of(aluno));
        when(alunoRepository.save(aluno)).thenReturn(aluno);

        Aluno result = alunoService.update(1, aluno);

        assertEquals(aluno, result);

        verify(alunoRepository, times(1)).findById(1);
        verify(alunoRepository, times(1)).save(aluno);
        verifyNoMoreInteractions(alunoRepository);
    }

    @Test
    void testUpdate_NotFound() {
        Aluno aluno = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));

        when(alunoRepository.findById(1)).thenReturn(Optional.empty());

        try {
            alunoService.update(1, aluno);
        } catch (ResponseStatusException ex) {
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
        }

        verify(alunoRepository, times(1)).findById(1);
        verifyNoMoreInteractions(alunoRepository);
    }
}