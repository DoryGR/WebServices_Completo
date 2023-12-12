package br.edu.ifgoias.academico.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CursoDTOTest {

    @Test
    public void testEqualsAndHashCode() {
        CursoDTO cursoDTO1 = new CursoDTO(1, "Engenharia de Software");
        CursoDTO cursoDTO2 = new CursoDTO(1, "Engenharia de Software");
        CursoDTO cursoDTO3 = new CursoDTO(2, "Ciência da Computação");

        assertTrue(cursoDTO1.equals(cursoDTO2));
        assertFalse(cursoDTO1.equals(cursoDTO3));

        assertEquals(cursoDTO1.hashCode(), cursoDTO2.hashCode());
        assertNotEquals(cursoDTO1.hashCode(), cursoDTO3.hashCode());
    }

}

