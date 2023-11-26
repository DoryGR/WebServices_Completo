package br.edu.ifgoias.academico.services;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifgoias.academico.entities.Aluno;

@SpringBootTest
@AutoConfigureMockMvc
public class AlunoResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRetornarTodosOsAlunos() throws Exception {
        List<Aluno> alunos = Arrays.asList(new Aluno(), new Aluno());
        when(alunoService.findAll()).thenReturn(alunos);

        mockMvc.perform(get("/alunos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(alunos)));
    }

    @Test
    void deveRetornarAlunoPorID() throws Exception {
        Integer alunoId = 1;
        Aluno alunoMock = new Aluno();
        alunoMock.setIdaluno(alunoId);
        alunoMock.setNome("Exemplo Aluno");
        alunoMock.setSexo("M");
        alunoMock.setDt_nasc(new Date());

        when(alunoService.findById(alunoId)).thenReturn(alunoMock);

        mockMvc.perform(get("/alunos/{id}", alunoId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(alunoMock)));
    }

}
