package br.edu.ifgoias.academico.resources;

import br.edu.ifgoias.academico.DTO.AlunoDTO;
import br.edu.ifgoias.academico.services.AlunoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(AlunoResource.class)
public class AlunoResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Test
    public void testFindAll() throws Exception {
        AlunoDTO aluno1 = new AlunoDTO(1, "João", "Masculino", "1990-01-01");
        AlunoDTO aluno2 = new AlunoDTO(2, "Maria", "Feminino", "1995-05-05");
        List<AlunoDTO> alunos = Arrays.asList(aluno1, aluno2);

        when(alunoService.findAllDTO()).thenReturn(alunos);

        mockMvc.perform(MockMvcRequestBuilders.get("/alunos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(alunos.size()));
    }

    @Test
    public void testFindById() throws Exception {
        AlunoDTO aluno = new AlunoDTO(1, "Joao", "Masculino", "1990-01-01");

        when(alunoService.findByIdDTO(1)).thenReturn(aluno);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/alunos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        assertThat(jsonResponse, containsString("\"idaluno\":" + aluno.getIdaluno()));
        assertThat(jsonResponse, containsString("\"nome\":\"" + aluno.getNome() + "\""));
        assertThat(jsonResponse, containsString("\"sexo\":\"" + aluno.getSexo() + "\""));
        assertThat(jsonResponse, containsString("\"dt_nasc\":\"" + aluno.getDtNasc() + "\""));
    }

    @Test
    public void testInsert() throws Exception {
        AlunoDTO alunoDTO = new AlunoDTO(null, "Carlos", "Masculino", "1998-08-08");

        when(alunoService.insertDTO(alunoDTO)).thenReturn(alunoDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nome\": \"Carlos\", \"sexo\": \"Masculino\", \"dt_nasc\": \"1998-08-08\" }"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        assertThat(jsonResponse, containsString("\"nome\":\"" + alunoDTO.getNome() + "\""));
        assertThat(jsonResponse, containsString("\"sexo\":\"" + alunoDTO.getSexo() + "\""));
        assertThat(jsonResponse, containsString("\"dt_nasc\":\"" + alunoDTO.getDtNasc() + "\""));

    }

@Test
    public void testUpdate() throws Exception {
        AlunoDTO alunoDTO = new AlunoDTO(null, "Carlos", "Masculino", "1998-08-08");

        when(alunoService.updateDTO(1, alunoDTO)).thenReturn(alunoDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/alunos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nome\": \"Carlos\", \"sexo\": \"Masculino\", \"dt_nasc\": \"1998-08-08\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        assertThat(jsonResponse, containsString("\"nome\":\"" + alunoDTO.getNome() + "\""));
        assertThat(jsonResponse, containsString("\"sexo\":\"" + alunoDTO.getSexo() + "\""));
        assertThat(jsonResponse, containsString("\"dt_nasc\":\"" + alunoDTO.getDtNasc() + "\""));

    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/alunos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
