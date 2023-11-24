/*package br.edu.ifgoias.academico.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifgoias.academico.entities.Curso;
import br.edu.ifgoias.academico.repositories.CursoRepository;

@SpringBootTest
public class CursoServiceTest {

    @MockBean
    private CursoRepository cursoRepository;

    private CursoService cursoService;

    @Test
    void deveRetornarTodosOsCursos() {
        when(cursoRepository.findAll()).thenReturn(Arrays.asList(new Curso(), new Curso()));

        cursoService = new CursoService(cursoRepository);

        List<Curso> cursos = cursoService.findAll();

        
        assertEquals(2, cursos.size());
    }

    @Test
    void deveBuscarCursoPorIDComSucesso() {
        Integer cursoId = 1;
        Curso cursoMock = new Curso();
        cursoMock.setIdcurso(cursoId);
        cursoMock.setNomecurso("Exemplo Curso");

        when(cursoRepository.findById(cursoId)).thenReturn(Optional.of(cursoMock));

        cursoService = new CursoService(cursoRepository);

        Curso cursoRetornado = cursoService.findById(cursoId);

        assertEquals(cursoMock.getIdcurso(), cursoRetornado.getIdcurso());
        assertEquals(cursoMock.getNomecurso(), cursoRetornado.getNomecurso());
    }

    @Test
    void deveLancarExcecaoAoBuscarCursoPorIDInexistente() {
        Integer cursoId = 1;

        when(cursoRepository.findById(cursoId)).thenReturn(Optional.empty());

        cursoService = new CursoService(cursoRepository);

        assertThrows(ResponseStatusException.class, () -> cursoService.findById(cursoId));
    }
}
*/
