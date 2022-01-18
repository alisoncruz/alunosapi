package br.com.alunosapi.service;

import br.com.alunosapi.exception.AlunoNaoEncontradoException;
import br.com.alunosapi.model.Aluno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AlunoService {

  private List<Aluno> alunos;

  public AlunoService() {
    this.alunos = new ArrayList<>();
  }

  public ResponseEntity<List<Aluno>> findAll(String nome, Integer idade) {
    if (nome != null) {
      List<Aluno> alunosList =
          alunos.stream()
              .filter(aluno -> aluno.getNome().contains(nome))
              .collect(Collectors.toList());
      return ResponseEntity.ok(alunosList);

    } else if (idade != null) {
      List<Aluno> alunos =
          this.alunos.stream()
              .filter(aluno -> aluno.getIdade().equals(idade))
              .collect(Collectors.toList());
      return ResponseEntity.ok(alunos);
    }
    return ResponseEntity.ok(alunos);
  }

  public ResponseEntity<Integer> add(final Aluno aluno) {
    if (aluno.getId() == null) {
      aluno.setId(alunos.size() + 1);
    }
    alunos.add(aluno);
    return ResponseEntity.status(HttpStatus.CREATED).body(aluno.getId());
  }

  public ResponseEntity<Aluno> findById(final Integer id) {
    Optional<Aluno> alunoOptional =
        alunos.stream().filter(aluno -> aluno.getId().equals(id)).findFirst();
    if (alunoOptional.isPresent()) {
      Aluno aluno = alunoOptional.get();
      return ResponseEntity.ok(aluno);
    } else {
      throw new AlunoNaoEncontradoException();
    }
  }

  public ResponseEntity update(final Integer id, final Aluno aluno) {
    Optional<Aluno> alunoOptional = alunos.stream().filter(a -> a.getId().equals(id)).findFirst();
    if (alunoOptional.isEmpty()) throw new AlunoNaoEncontradoException();

    alunos.stream()
        .filter(a -> a.getId().equals(id))
        .forEach(
            a -> {
              a.setNome(aluno.getNome());
              a.setIdade(aluno.getIdade());
            });
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  public ResponseEntity delete(final Integer id) {
    boolean removed = alunos.removeIf(aluno -> aluno.getId().equals(id));
    if (removed) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    throw new AlunoNaoEncontradoException();
  }

  public ResponseEntity<Aluno> findByNome(final String nome) {
    Optional<Aluno> alunoOptional =
        alunos.stream().filter(aluno -> aluno.getNome().equalsIgnoreCase(nome)).findFirst();
    if (alunoOptional.isPresent()) {
      Aluno aluno = alunoOptional.get();
      return ResponseEntity.ok(aluno);
    }
    throw new AlunoNaoEncontradoException();
  }
}
