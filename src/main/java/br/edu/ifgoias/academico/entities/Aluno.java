package br.edu.ifgoias.academico.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idaluno;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "sexo", nullable = false)
	private String sexo;

	@Column(name = "dt_nasc", nullable = false)
	private LocalDate dt_nasc;

	@ManyToOne
	@JoinColumn(name = "idcurso")
	private Curso curso;

	public Aluno() {

	}

	public Aluno(Integer id, String nome, String sexo, LocalDate of) {
		this.idaluno = id;
		this.nome = nome;
		this.sexo = sexo;
		this.dt_nasc = of;
	}

    public Aluno(int id, String joão, String masculino, LocalDate of) {
    }

    public Integer getIdaluno() {
		return idaluno;
	}

	public void setIdaluno(Integer idaluno) {
		this.idaluno = idaluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDt_nasc() {
		return dt_nasc;
	}

	public void setDt_nasc(LocalDate dt_nasc) {
		this.dt_nasc = dt_nasc;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso c) {
		this.curso = c;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dt_nasc, idaluno, nome, sexo);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Aluno aluno = (Aluno) o;
		return Objects.equals(idaluno, aluno.idaluno) &&
				Objects.equals(nome, aluno.nome) &&
				Objects.equals(sexo, aluno.sexo) &&
				Objects.equals(dt_nasc, aluno.dt_nasc);
	}


	@Override
	public String toString() {
		return "Aluno [idaluno=" + idaluno + ", nome=" + nome + ", sexo=" + sexo + ", dt_nasc=" + dt_nasc + "]";
	}


}
