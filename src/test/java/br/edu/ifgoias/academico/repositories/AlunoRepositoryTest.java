package br.edu.ifgoias.academico.repositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import br.edu.ifgoias.academico.entities.Aluno;

@DataJpaTest
public class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    public void testFindAll() {
        Aluno aluno1 = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));
        Aluno aluno2 = new Aluno(2, "Maria", "Feminino", LocalDate.of(2001, 2, 2));
        alunoRepository.save(aluno1);
        alunoRepository.save(aluno2);

        List<Aluno> alunos = alunoRepository.findAll();

        assertEquals(2, alunos.size());
    }
    @Test
    public void testFindById() {
        Aluno aluno = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));
        alunoRepository.save(aluno);

        Optional<Aluno> alunoEncontrado = alunoRepository.findById(1);

        assertTrue(alunoEncontrado.isPresent());
        assertEquals(aluno.getNome(), alunoEncontrado.get().getNome());
    }

    @Test
    public void testSave() {
        Aluno aluno = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));
        Aluno alunoSalvo = alunoRepository.save(aluno);

        assertNotNull(alunoSalvo.getIdaluno());
        assertEquals(aluno.getNome(), alunoSalvo.getNome());
    }

    @Test
    public void testDelete() {
        Aluno aluno = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));
        alunoRepository.save(aluno);

        alunoRepository.deleteById(1);

        Optional<Aluno> alunoRemovido = alunoRepository.findById(1);
        assertFalse(alunoRemovido.isPresent());
    }
}

