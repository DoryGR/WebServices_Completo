package br.edu.ifgoias.academico.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AlunoDTO {
    private Integer idaluno;
    private String nome;
    private String sexo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("dt_nasc")
    private String dtNasc;

    

    public AlunoDTO(Integer idaluno, String nome, String sexo, String dtNasc) {
        this.idaluno = idaluno;
        this.nome = nome;
        this.sexo = sexo;
        this.dtNasc = dtNasc;
    }

    public Integer getIdaluno() {
        return idaluno;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlunoDTO alunoDTO = (AlunoDTO) o;
        return Objects.equals(idaluno, alunoDTO.idaluno) &&
                Objects.equals(nome, alunoDTO.nome) &&
                Objects.equals(sexo, alunoDTO.sexo) &&
                Objects.equals(dtNasc, alunoDTO.dtNasc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idaluno, nome, sexo, dtNasc);
    }

    @Override
    public String toString() {
        return "AlunoDTO{" +
                "idaluno=" + idaluno +
                ", nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", dtNasc='" + dtNasc + '\'' +
                '}';
    }
}
