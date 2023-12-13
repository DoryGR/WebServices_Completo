package br.edu.ifgoias.academico.entities;

import org.junit.jupiter.api.Test;

import br.edu.ifgoias.academico.dto.AlunoDTO;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlunoTest {

    @Test
    void testEquals() {
        AlunoDTO alunoDTO1 = new AlunoDTO(1, "João", "Masculino", "2000-01-01");
        AlunoDTO alunoDTO2 = new AlunoDTO(1, "João", "Masculino", "2000-01-01");
        AlunoDTO alunoDTO3 = new AlunoDTO(2, "Maria", "Feminino", "1999-12-31");

        assertEquals(alunoDTO1, alunoDTO2);
        assertNotEquals(alunoDTO1, alunoDTO3);
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
    @Test
    void testGettersAndSetters() {
        Aluno aluno = new Aluno();
        aluno.setIdaluno(1);
        aluno.setNome("João");
        aluno.setSexo("Masculino");
        aluno.setDt_nasc(LocalDate.of(2000, 1, 1));

        assertEquals(1, aluno.getIdaluno(), "Expected ID to be 1");
        assertEquals("João", aluno.getNome(), "Expected name to be João");
        assertEquals("Masculino", aluno.getSexo(), "Expected gender to be Masculino");
        assertEquals(LocalDate.of(2000, 1, 1), aluno.getDt_nasc(), "Expected birth date to be 2000-01-01");

        aluno.setIdaluno(2);
        aluno.setNome("Maria");
        aluno.setSexo("Feminino");
        aluno.setDt_nasc(LocalDate.of(1999, 12, 31));

        assertEquals(2, aluno.getIdaluno(), "Expected ID to be 2");
        assertEquals("Maria", aluno.getNome(), "Expected name to be Maria");
        assertEquals("Feminino", aluno.getSexo(), "Expected gender to be Feminino");
        assertEquals(LocalDate.of(1999, 12, 31), aluno.getDt_nasc(), "Expected birth date to be 1999-12-31");

        aluno.setDt_nasc(null);
        assertNull(aluno.getDt_nasc(), "Expected birth date to be null after setting to null");
    }
    @Test
    void testEqualsDifferentNome() {
        Aluno aluno1 = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));
        Aluno aluno2 = new Aluno(1, "Maria", "Masculino", LocalDate.of(2000, 1, 1));

        assertNotEquals(aluno1, aluno2);
    }

    @Test
    void testEqualsDifferentSexo() {
        Aluno aluno1 = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));
        Aluno aluno2 = new Aluno(1, "João", "Feminino", LocalDate.of(2000, 1, 1));

        assertNotEquals(aluno1, aluno2);
    }

    @Test
    void testEqualsDifferentDtNasc() {
        Aluno aluno1 = new Aluno(1, "João", "Masculino", LocalDate.of(2000, 1, 1));
        Aluno aluno2 = new Aluno(1, "João", "Masculino", LocalDate.of(1999, 12, 31));

        assertNotEquals(aluno1, aluno2);
    }

}