package br.edu.ifgoias.academico.repositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import br.edu.ifgoias.academico.entities.Curso;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    @Test
    public void testFindAll() {
        Curso curso1 = new Curso(1, "Ciência da Computação");
        Curso curso2 = new Curso(2, "Engenharia Elétrica");
        cursoRepository.save(curso1);
        cursoRepository.save(curso2);

        List<Curso> cursos = cursoRepository.findAll();

        assertEquals(2, cursos.size());
    }

    @Test
    public void testFindById() {
        Curso curso = new Curso(1, "Ciência da Computação");
        cursoRepository.save(curso);

        Curso cursoEncontrado = cursoRepository.findById(1).orElse(null);

        assertNotNull(cursoEncontrado);
        assertEquals(curso.getNomecurso(), cursoEncontrado.getNomecurso());
    }

    @Test
    public void testSave() {
        Curso curso = new Curso(1, "Ciência da Computação");
        Curso cursoSalvo = cursoRepository.save(curso);

        assertNotNull(cursoSalvo.getIdcurso());
        assertEquals(curso.getNomecurso(), cursoSalvo.getNomecurso());
    }

    @Test
    public void testDelete() {
        Curso curso = new Curso(1, "Ciência da Computação");
        cursoRepository.save(curso);

        cursoRepository.deleteById(1);

        Curso cursoRemovido = cursoRepository.findById(1).orElse(null);
        assertNull(cursoRemovido);
    }
}

