package br.edu.ifgoias.academico.DTO;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AlunoDTOTest {

    @Test
    public void testEqualsAndHashCode() {
        AlunoDTO alunoDTO1 = new AlunoDTO(1, "João", "M", new Date());
        AlunoDTO alunoDTO2 = new AlunoDTO(1, "João", "M", new Date());
        AlunoDTO alunoDTO3 = new AlunoDTO(2, "Maria", "F", new Date());

        assertTrue(alunoDTO1.equals(alunoDTO2));
        assertFalse(alunoDTO1.equals(alunoDTO3));

        assertEquals(alunoDTO1.hashCode(), alunoDTO2.hashCode());
        assertNotEquals(alunoDTO1.hashCode(), alunoDTO3.hashCode());
    }

}

