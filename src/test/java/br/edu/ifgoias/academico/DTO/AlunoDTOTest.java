package br.edu.ifgoias.academico.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoDTOTest {

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
        AlunoDTO alunoDTO1 = new AlunoDTO(1, "João", "Masculino", "2000-01-01");
        AlunoDTO alunoDTO2 = new AlunoDTO(1, "João", "Masculino", "2000-01-01");
        AlunoDTO alunoDTO3 = new AlunoDTO(2, "Maria", "Feminino", "1999-12-31");

        assertEquals(alunoDTO1.hashCode(), alunoDTO2.hashCode());
        assertNotEquals(alunoDTO1.hashCode(), alunoDTO3.hashCode());
    }

    @Test
    void testGetters() {
        AlunoDTO alunoDTO = new AlunoDTO(1, "João", "Masculino", "2000-01-01");

        assertEquals(1, alunoDTO.getIdaluno());
        assertEquals("João", alunoDTO.getNome());
        assertEquals("Masculino", alunoDTO.getSexo());
        assertEquals("2000-01-01", alunoDTO.getDtNasc());
    }

    @Test
    void testToString() {
        AlunoDTO alunoDTO = new AlunoDTO(1, "João", "Masculino", "2000-01-01");

        assertEquals("AlunoDTO{idaluno=1, nome='João', sexo='Masculino', dtNasc='2000-01-01'}", alunoDTO.toString());
    }
}