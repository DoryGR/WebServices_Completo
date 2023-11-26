package br.edu.ifgoias.academico.entities;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class AlunoTest {

    @Test
    void testEquals() {
        Aluno aluno1 = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));
        Aluno aluno2 = new Aluno(2, "Maria", "Feminino", LocalDate.of(1999, 12, 31));
        Aluno aluno3 = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1)); // Mesmo conteúdo que aluno1

        assertNotEquals(aluno1, aluno2); // Garante que alunos com valores diferentes não são iguais
        assertNotEquals(aluno1, aluno3); // Garante que mesmo conteúdo não resulta em igualdade (a menos que você deseje que seja assim)
    }

    @Test
    public void testHashCode() {
        Aluno aluno1 = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));
        Aluno aluno2 = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));

        assertEquals(aluno1.hashCode(), aluno2.hashCode());
    }

    @Test
    public void testToString() {
        Aluno aluno = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));

        String expectedToString = "Aluno [idaluno=1, nome=João, sexo=Masculino, dt_nasc=2000-01-01]";
        assertEquals(expectedToString, aluno.toString());
    }
}


