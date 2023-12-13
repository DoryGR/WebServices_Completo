package br.edu.ifgoias.academico.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CursoDTOTest {

    @Test
    void testEquals() {
        CursoDTO cursoDTO1 = new CursoDTO(1, "Ciência da Computação");
        CursoDTO cursoDTO2 = new CursoDTO(1, "Ciência da Computação");
        CursoDTO cursoDTO3 = new CursoDTO(2, "Engenharia Elétrica");

        assertEquals(cursoDTO1, cursoDTO2);
        assertNotEquals(cursoDTO1, cursoDTO3);
    }

    @Test
    void testHashCode() {
        CursoDTO cursoDTO1 = new CursoDTO(1, "Ciência da Computação");
        CursoDTO cursoDTO2 = new CursoDTO(1, "Ciência da Computação");
        CursoDTO cursoDTO3 = new CursoDTO(2, "Engenharia Elétrica");

        assertEquals(cursoDTO1.hashCode(), cursoDTO2.hashCode());
        assertNotEquals(cursoDTO1.hashCode(), cursoDTO3.hashCode());
    }

    @Test
    void testGetters() {
        CursoDTO cursoDTO = new CursoDTO(1, "Ciência da Computação");

        assertEquals(1, cursoDTO.getIdcurso());
        assertEquals("Ciência da Computação", cursoDTO.getNomecurso());
    }

    @Test
    void testSetNomecurso() {
        CursoDTO cursoDTO = new CursoDTO(1, "Ciência da Computação");
        cursoDTO.setNomecurso("Engenharia de Software");

        assertEquals("Engenharia de Software", cursoDTO.getNomecurso());
    }

    @Test
    void testToString() {
        CursoDTO cursoDTO = new CursoDTO(1, "Ciência da Computação");

        assertEquals("CursoDTO{idcurso=1, nomecurso='Ciência da Computação'}", cursoDTO.toString());
    }
}
