package br.edu.ifgoias.academico.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoias.academico.DTO.AlunoDTO;
import br.edu.ifgoias.academico.entities.Aluno;
import br.edu.ifgoias.academico.services.AlunoService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/alunos")
public class AlunoResource {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll() {
        List<Aluno> alunos = alunoService.findAll();
        List<AlunoDTO> alunoDTOs = alunos.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(alunoDTOs);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable Integer id) {
        Aluno aluno = alunoService.findById(id);
        AlunoDTO alunoDTO = convertToDTO(aluno);
        return ResponseEntity.ok().body(alunoDTO);
    }

    private AlunoDTO convertToDTO(Aluno aluno) {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getIdaluno());
        alunoDTO.setName(aluno.getNome());
        return alunoDTO;
    }
}