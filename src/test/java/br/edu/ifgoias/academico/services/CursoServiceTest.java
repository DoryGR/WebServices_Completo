package br.edu.ifgoias.academico.services;

import br.edu.ifgoias.academico.dto.CursoDTO;
import br.edu.ifgoias.academico.entities.Curso;
import br.edu.ifgoias.academico.repositories.CursoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CursoServiceTest {

    @Autowired
    private CursoService cursoService;

    @MockBean
    private CursoRepository cursoRepository;

    @Test
    void testFindAllDTO() {
        Curso curso = new Curso(1, "Ciência da Computação");
        when(cursoRepository.findAll()).thenReturn(Collections.singletonList(curso));

        assertEquals(1, cursoService.findAllDTO().size());
    }

    @Test
    void testFindByIdDTO() {
        Curso curso = new Curso(1, "Ciência da Computação");
        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));

        assertEquals("Ciência da Computação", cursoService.findByIdDTO(1).getNomecurso());
    }

    @Test
    void testInsertDTO() {
        CursoDTO cursoDTO = new CursoDTO(null, "Engenharia Elétrica");
        when(cursoRepository.save(any())).thenReturn(new Curso(1, "Engenharia Elétrica"));

        assertEquals("Engenharia Elétrica", cursoService.insertDTO(cursoDTO).getNomecurso());
    }

    @Test
    void testDelete() {
        cursoService.delete(1);

        verify(cursoRepository, times(1)).deleteById(1);
    }

    @Test
    void testUpdateDTO() {
        Curso curso = new Curso(1, "Ciência da Computação");
        CursoDTO cursoDTO = new CursoDTO(null, "Engenharia de Software");
        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));
        when(cursoRepository.save(any())).thenReturn(new Curso(1, "Engenharia de Software"));

        assertEquals("Engenharia de Software", cursoService.updateDTO(1, cursoDTO).getNomecurso());
    }
}
