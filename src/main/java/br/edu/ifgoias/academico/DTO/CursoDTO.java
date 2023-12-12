package br.edu.ifgoias.academico.dto;

import java.util.Objects;

public class CursoDTO {
    private Integer idcurso;
    private String nomecurso;

    public CursoDTO() {

    }

    public CursoDTO(Integer idcurso, String nomecurso) {
        this.idcurso = idcurso;
        this.nomecurso = nomecurso;
    }

    public Integer getIdcurso() {
        return idcurso;
    }

    public String getNomecurso() {
        return nomecurso;
    }

    public void setNomecurso(String nomecurso) {
        this.nomecurso = nomecurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoDTO cursoDTO = (CursoDTO) o;
        return Objects.equals(idcurso, cursoDTO.idcurso) &&
                Objects.equals(nomecurso, cursoDTO.nomecurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcurso, nomecurso);
    }
    
    @Override
    public String toString() {
        return "CursoDTO{" +
                "idcurso=" + idcurso +
                ", nomecurso='" + nomecurso + '\'' +
                '}';
    }
}
