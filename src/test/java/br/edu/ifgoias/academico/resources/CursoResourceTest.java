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
        cursos.add(new Curso(1, "Matemática"));
        cursos.add(new Curso(2, "História"));

        when(cursoService.findAll()).thenReturn(cursos);

        ResponseEntity<List<Curso>> response = cursoResource.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cursos, response.getBody());
    }

    @Test
    void testFindById() {
        Curso curso = new Curso(1, "Matemática");

        when(cursoService.findById(1)).thenReturn(curso);

        ResponseEntity<Curso> response = cursoResource.findById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(curso, response.getBody());
    }

    @Test
    void testInsert() {
        Curso curso = new Curso(1, "Matemática");

        when(cursoService.insert(curso)).thenReturn(curso);

        ResponseEntity<Curso> response = cursoResource.insert(curso);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(curso, response.getBody());
    }

    @Test
    void testDelete() {
        ResponseEntity<Void> response = cursoResource.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cursoService, times(1)).delete(1);
    }

    @Test
    void testUpdate() {
        Curso curso = new Curso(1, "Matemática");

        when(cursoService.update(1, curso)).thenReturn(curso);

        ResponseEntity<Curso> response = cursoResource.update(1, curso);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(curso, response.getBody());
    }
}

