package br.edu.ifgoias.academico.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoias.academico.entities.Curso;
import br.edu.ifgoias.academico.services.CursoService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/cursos")
public class CursoResource {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public ResponseEntity< List<Curso> >  findAll(){
		
		List<Curso> cursos = cursoService.findAll();
		
		return ResponseEntity.ok().body(cursos);
		
	}
	
	@GetMapping (value = "/{id}")
	public ResponseEntity<Curso> findById(@PathVariable Integer id){
		
		Curso curso = cursoService.findById(id);
		
		return ResponseEntity.ok().body(curso);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Curso> insert(@RequestBody Curso curso) {
		Curso createdCurso = cursoService.insert(curso);
		return new ResponseEntity<>(createdCurso, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		cursoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Curso> update(@PathVariable Integer id, @RequestBody Curso c  ){
		
		c = cursoService.update(id, c);
		
		return ResponseEntity.ok().body(c);
	}

}
