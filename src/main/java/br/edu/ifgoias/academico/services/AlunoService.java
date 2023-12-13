package br.edu.ifgoias.academico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifgoias.academico.dto.AlunoDTO;
import br.edu.ifgoias.academico.entities.Aluno;
import br.edu.ifgoias.academico.repositories.AlunoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<AlunoDTO> findAllDTO() {
        List<Aluno> alunos = alunoRepository.findAll();
        return alunos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public AlunoDTO findByIdDTO(Integer id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND));
        return convertToDTO(aluno);
    }

    public AlunoDTO insertDTO(AlunoDTO alunoDTO) {
		Aluno aluno = convertToEntity(alunoDTO);
		return convertToDTO(alunoRepository.save(aluno));
	}

    public ResponseEntity<Void> delete(Integer id) {
        alunoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public AlunoDTO updateDTO(Integer id, AlunoDTO alunoDTO) {
		return alunoRepository.findById(id).map(alunoDB -> {
			alunoDB.setNome(alunoDTO.getNome());
			alunoDB.setSexo(alunoDTO.getSexo());
	
			if (alunoDTO.getDtNasc() != null && !alunoDTO.getDtNasc().isEmpty()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dtNasc = LocalDate.parse(alunoDTO.getDtNasc(), formatter);
				alunoDB.setDt_nasc(dtNasc);
			} else {
				alunoDB.setDt_nasc(null);
			}
	
			alunoDB = alunoRepository.save(alunoDB);
	
			Aluno updatedAluno = alunoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
			return convertToDTO(updatedAluno);
		}).orElseThrow(() -> new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND));
	}
	

    private AlunoDTO convertToDTO(Aluno aluno) {
        return new AlunoDTO(
                aluno.getIdaluno(),
                aluno.getNome(),
                aluno.getSexo(),
                aluno.getDt_nasc() != null ? aluno.getDt_nasc().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null
        );
    }

	public Aluno convertToEntity(AlunoDTO alunoDTO) {
        if (alunoDTO.getDtNasc() != null && !alunoDTO.getDtNasc().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dtNasc = LocalDate.parse(alunoDTO.getDtNasc(), formatter);
            return new Aluno(
                    alunoDTO.getIdaluno(),
                    alunoDTO.getNome(),
                    alunoDTO.getSexo(),
                    dtNasc
            );
        } else {
            return new Aluno(
                    alunoDTO.getIdaluno(),
                    alunoDTO.getNome(),
                    alunoDTO.getSexo(),
                    null
            );
        }
    }
}
