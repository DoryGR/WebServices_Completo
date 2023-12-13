package br.edu.ifgoias.academico.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

import br.edu.ifgoias.academico.dto.AlunoDTO;
import br.edu.ifgoias.academico.entities.Aluno;
import br.edu.ifgoias.academico.repositories.AlunoRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepository;

    @Test
    void testFindAllDTO() {
        Aluno aluno = new Aluno(1, "João", "Masculino", LocalDate.now());
        when(alunoRepository.findAll()).thenReturn(Collections.singletonList(aluno));

        List<AlunoDTO> result = alunoService.findAllDTO();

        assertEquals(1, result.size(), "Expected one student in the list");
        
        AlunoDTO alunoDTO = result.get(0);
        
        assertNotNull(alunoDTO.getNome(), "Expected student name to be not null. AlunoDTO: " + alunoDTO);
        assertEquals("João", alunoDTO.getNome(), "Expected student name to be João. AlunoDTO: " + alunoDTO);
        assertNotNull(alunoDTO.getDtNasc(), "Expected birth date to be not null. AlunoDTO: " + alunoDTO);
    }

    @Test
    void testInsertDTO() {
        AlunoDTO alunoDTO = new AlunoDTO(null, "Maria", "Feminino", "2000-01-01");
        Aluno savedAluno = new Aluno(1, "Maria", "Feminino", LocalDate.of(2000, 1, 1));
        when(alunoRepository.save(any(Aluno.class))).thenReturn(savedAluno);

        AlunoDTO result = alunoService.insertDTO(alunoDTO);

        assertNotNull(result, "Expected result to be not null");
        assertEquals("Maria", result.getNome(), "Expected student name to be Maria");
        assertNotNull(result.getDtNasc(), "Expected birth date to be not null");
    }

    @Test
    void testFindByIdDTO() {
        Aluno aluno = new Aluno(1, "João", "Masculino", LocalDate.now());
        when(alunoRepository.findById(1)).thenReturn(Optional.of(aluno));

        AlunoDTO result = alunoService.findByIdDTO(1);

        assertNotNull(result, "Expected result to be not null");
        assertEquals("João", result.getNome(), "Expected student name to be João");
        assertNotNull(result.getDtNasc(), "Expected birth date to be not null");
    }


    @Test
    void testDelete() {

        ResponseEntity<Void> result = alunoService.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(alunoRepository, times(1)).deleteById(1);
    }

    @Test
    void testUpdateDTO() {
        Aluno aluno = new Aluno(1, "João", "Masculino", LocalDate.now());
        AlunoDTO alunoDTO = new AlunoDTO(null, "Maria", "Feminino", "2000-01-01");
        when(alunoRepository.findById(1)).thenReturn(java.util.Optional.of(aluno));
        when(alunoRepository.save(any(Aluno.class))).thenReturn(new Aluno(1, "Maria", "Feminino", LocalDate.parse("2000-01-01")));

        AlunoDTO result = alunoService.updateDTO(1, alunoDTO);

        assertNotNull(result);
        assertEquals("Maria", result.getNome());
    }

    @Test
    void testConvertToEntity() {
        AlunoDTO alunoDTO = new AlunoDTO(1, "João", "Masculino", "2000-01-01");
        
        Aluno result = alunoService.convertToEntity(alunoDTO);

        assertNotNull(result, "Expected result to be not null");
        assertEquals("João", result.getNome(), "Expected student name to be João");
        assertEquals("Masculino", result.getSexo(), "Expected student gender to be Masculino");
        assertEquals(LocalDate.parse("2000-01-01"), result.getDt_nasc(), "Expected birth date to be 2000-01-01");
    }

}
