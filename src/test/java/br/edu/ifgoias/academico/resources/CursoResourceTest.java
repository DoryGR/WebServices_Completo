package br.edu.ifgoias.academico.resources;

import br.edu.ifgoias.academico.DTO.CursoDTO;
import br.edu.ifgoias.academico.services.CursoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(CursoResource.class)
public class CursoResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Test
    public void testFindAll() throws Exception {
        CursoDTO curso1 = new CursoDTO(1, "Ciências da Computação");
        CursoDTO curso2 = new CursoDTO(2, "Engenharia Elétrica");
        List<CursoDTO> cursos = Arrays.asList(curso1, curso2);

        when(cursoService.findAllDTO()).thenReturn(cursos);

        mockMvc.perform(MockMvcRequestBuilders.get("/cursos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(cursos.size()));
    }

    @Test
    public void testFindById() throws Exception {
        CursoDTO curso = new CursoDTO(1, "Ciências da Computação");

        when(cursoService.findByIdDTO(1)).thenReturn(curso);

        mockMvc.perform(MockMvcRequestBuilders.get("/cursos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idcurso").value(curso.getIdcurso()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nomecurso").value(curso.getNomecurso()));
    }

    @Test
    public void testInsert() throws Exception {
        CursoDTO cursoDTO = new CursoDTO(null, "Engenharia Mecânica");

        when(cursoService.insertDTO(cursoDTO)).thenReturn(cursoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/cursos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nomecurso\": \"Engenharia Mecânica\" }"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nomecurso").value(cursoDTO.getNomecurso()));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/cursos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testUpdate() throws Exception {
        CursoDTO cursoDTO = new CursoDTO(null, "Engenharia Civil");

        when(cursoService.updateDTO(1, cursoDTO)).thenReturn(cursoDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/cursos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nomecurso\": \"Engenharia Civil\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nomecurso").value(cursoDTO.getNomecurso()));
    }
}
