package br.edu.ifgoias.academico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifgoias.academico.DTO.CursoDTO;
import br.edu.ifgoias.academico.entities.Curso;
import br.edu.ifgoias.academico.repositories.CursoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<CursoDTO> findAllDTO() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public CursoDTO findByIdDTO(Integer id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDTO(curso);
    }

    public CursoDTO insertDTO(CursoDTO cursoDTO) {
        Curso curso = convertToEntity(cursoDTO);
        return convertToDTO(cursoRepository.save(curso));
    }

    public void delete(Integer id) {
        cursoRepository.deleteById(id);
    }

    public CursoDTO updateDTO(Integer id, CursoDTO cursoDTO) {
        return cursoRepository.findById(id).map(cursoDB -> {
            cursoDB.setNomecurso(cursoDTO.getNomecurso());
            return convertToDTO(cursoRepository.save(cursoDB));
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private CursoDTO convertToDTO(Curso curso) {
        return new CursoDTO(
                curso.getIdcurso(),
                curso.getNomecurso()
        );
    }

    private Curso convertToEntity(CursoDTO cursoDTO) {
        return new Curso(
                cursoDTO.getIdcurso(),
                cursoDTO.getNomecurso()
        );
    }
}
