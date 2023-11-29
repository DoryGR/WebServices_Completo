package br.edu.ifgoias.academico.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AlunoTest {

    @Test
    void testEquals() {
        Aluno aluno1 = new Aluno();
        aluno1.setIdaluno(1);
        aluno1.setNome("João");
        aluno1.setSexo("Masculino");
        aluno1.setDt_nasc(LocalDate.of(2000, 1, 1));
    
        Aluno aluno2 = new Aluno();
        aluno2.setIdaluno(1);
        aluno2.setNome("João");
        aluno2.setSexo("Masculino");
        aluno2.setDt_nasc(LocalDate.of(2000, 1, 1));
    
        assertEquals(aluno1, aluno2);
    }

    @Test
    void testHashCode() {
        Aluno aluno1 = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));
        Aluno aluno2 = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));

        assertEquals(aluno1.hashCode(), aluno2.hashCode());
    }

    @Test
    void testToString() {
        Aluno aluno = new Aluno();
        aluno.setIdaluno(1);
        aluno.setNome("João");
        aluno.setSexo("Masculino");
        aluno.setDt_nasc(LocalDate.of(2000, 1, 1));

        String expected = "Aluno [idaluno=1, nome=João, sexo=Masculino, dt_nasc=2000-01-01]";
        String actual = aluno.toString();

        assertEquals(expected, actual);
    }
}