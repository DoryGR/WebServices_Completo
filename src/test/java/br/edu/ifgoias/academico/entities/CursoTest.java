package br.edu.ifgoias.academico.entities;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class CursoTest {

    @Test
    public void testAddAluno() {
        Curso curso = new Curso(1, "Ciência da Computação");
        Aluno aluno = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));

        curso.addAluno(aluno);

        assertTrue(curso.getAlunos().contains(aluno));
        assertEquals(curso, aluno.getCurso());
    }

    @Test
    public void testRemoveAluno() {
        Curso curso = new Curso(1, "Ciência da Computação");
        Aluno aluno = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));

        curso.addAluno(aluno);
        curso.removeAluno(aluno);

        assertFalse(curso.getAlunos().contains(aluno));
        assertNull(aluno.getCurso());
    }

    @Test
    public void testToString() {
        Curso curso = new Curso(1, "Ciência da Computação");
        String expectedToString = "Curso [idcurso=1, nomecurso=Ciência da Computação]";

        assertEquals(expectedToString, curso.toString());
    }
}

