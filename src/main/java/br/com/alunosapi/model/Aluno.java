package br.com.alunosapi.model;

import java.util.Objects;

public class Aluno {
  private Integer id;
  private String nome;
  private Integer idade;

  public Aluno() {}

  public Aluno(String nome, Integer idade) {
    this.nome = nome;
    this.idade = idade;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Integer getIdade() {
    return idade;
  }

  public void setIdade(Integer idade) {
    this.idade = idade;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Aluno aluno = (Aluno) o;
    return Objects.equals(id, aluno.id)
        && Objects.equals(nome, aluno.nome)
        && Objects.equals(idade, aluno.idade);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, idade);
  }

  @Override
  public String toString() {
    return "Aluno{" + "id=" + id + ", nome='" + nome + '\'' + ", idade=" + idade + '}';
  }
}
