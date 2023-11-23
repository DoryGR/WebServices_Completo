package br.edu.ifgoias.academico.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifgoias.academico.entities.Aluno;
import br.edu.ifgoias.academico.repositories.AlunoRepository;

@SpringBootTest
public class AlunoServiceTest {

    @MockBean
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoService alunoService;

    @Test
    void deveRetornarTodosOsAlunos() {
        when(alunoRepository.findAll()).thenReturn(Arrays.asList(new Aluno(), new Aluno()));

        List<Aluno> alunos = alunoService.findAll();

        assertEquals(2, alunos.size());
    }

    @Test
    void deveBuscarAlunoPorIDComSucesso() {
        Integer alunoId = 1;
        Aluno alunoMock = new Aluno();
        alunoMock.setIdaluno(alunoId);
        alunoMock.setNome("Exemplo Aluno");

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(alunoMock));

        Aluno alunoRetornado = alunoService.findById(alunoId);

        assertEquals(alunoMock.getIdaluno(), alunoRetornado.getIdaluno());
        assertEquals(alunoMock.getNome(), alunoRetornado.getNome());
    }

    @Test
    void deveLancarExcecaoAoBuscarAlunoPorIDInexistente() {
        Integer alunoId = 1;

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> alunoService.findById(alunoId));
    }
}
