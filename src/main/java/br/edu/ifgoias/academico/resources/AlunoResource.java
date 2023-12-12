package br.edu.ifgoias.academico.resources;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifgoias.academico.dto.AlunoDTO;
import br.edu.ifgoias.academico.services.AlunoService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/alunos")
public class AlunoResource {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll() {
        List<AlunoDTO> alunosDTO = alunoService.findAllDTO();
        return ResponseEntity.ok().body(alunosDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable Integer id) {
        AlunoDTO alunoDTO = alunoService.findByIdDTO(id);
        return ResponseEntity.ok().body(alunoDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AlunoDTO> insert(@RequestBody AlunoDTO alunoDTO) {
        AlunoDTO createdAlunoDTO = alunoService.insertDTO(alunoDTO);
        return new ResponseEntity<>(createdAlunoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AlunoDTO> update(@PathVariable Integer id, @RequestBody AlunoDTO alunoDTO) {
        AlunoDTO updatedAlunoDTO = alunoService.updateDTO(id, alunoDTO);
        return ResponseEntity.ok().body(updatedAlunoDTO);
    }
}
