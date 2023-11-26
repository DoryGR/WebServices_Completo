package br.edu.ifgoias.academico.services;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifgoias.academico.entities.Curso;

@SpringBootTest
@AutoConfigureMockMvc
public class CursoResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRetornarTodosOsCursos() throws Exception {
        // Configuração do mock para retornar uma lista de cursos
        List<Curso> cursos = Arrays.asList(new Curso(), new Curso());
        when(cursoService.findAll()).thenReturn(cursos);

        // Execução da requisição GET para /cursos
        mockMvc.perform(get("/cursos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cursos)));
    }

    @Test
    void deveRetornarCursoPorID() throws Exception {
        // Dados de exemplo
        Integer cursoId = 1;
        Curso cursoMock = new Curso();
        cursoMock.setIdcurso(cursoId);
        cursoMock.setNomecurso("Exemplo Curso");

        // Configuração do mock para retornar o cursoMock quando o método findById for chamado
        when(cursoService.findById(cursoId)).thenReturn(cursoMock);

        // Execução da requisição GET para /cursos/{id}
        mockMvc.perform(get("/cursos/{id}", cursoId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cursoMock)));
    }

    // Adicione mais testes conforme necessário para cobrir os métodos insert, delete e update
}
