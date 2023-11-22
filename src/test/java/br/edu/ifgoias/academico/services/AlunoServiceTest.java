package br.edu.ifgoias.academico.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import br.edu.ifgoias.academico.entities.Aluno;
import br.edu.ifgoias.academico.repositories.AlunoRepository;
import br.edu.ifgoias.academico.services.AlunoService;

@SpringBootTest
public class AlunoServiceTest {

    @Autowired
    private AlunoService alunoService;

    @MockBean
    private AlunoRepository alunoRepository;

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

}
