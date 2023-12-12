package br.edu.ifgoias.academico.resources;

import br.edu.ifgoias.academico.dto.CursoDTO;
import br.edu.ifgoias.academico.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/cursos")
public class CursoResource {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> findAll() {
        List<CursoDTO> cursosDTO = cursoService.findAllDTO();
        return ResponseEntity.ok().body(cursosDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CursoDTO> findById(@PathVariable Integer id) {
        CursoDTO cursoDTO = cursoService.findByIdDTO(id);
        return ResponseEntity.ok().body(cursoDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CursoDTO> insert(@RequestBody CursoDTO cursoDTO) {
        CursoDTO createdCursoDTO = cursoService.insertDTO(cursoDTO);
        return new ResponseEntity<>(createdCursoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CursoDTO> update(@PathVariable Integer id, @RequestBody CursoDTO cursoDTO) {
        CursoDTO updatedCursoDTO = cursoService.updateDTO(id, cursoDTO);
        return ResponseEntity.ok().body(updatedCursoDTO);
    }
}