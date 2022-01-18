package br.com.alunosapi;

import br.com.alunosapi.model.Aluno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

  private List<Aluno> alunos;

  public AlunoController() {
    this.alunos = new ArrayList<>();
  }

  @GetMapping
  public ResponseEntity<List<Aluno>> findAll(
      @RequestParam(required = false) String nome, @RequestParam(required = false) Integer idade) {
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

  @PostMapping
  public ResponseEntity<Integer> add(@RequestBody final Aluno aluno) {
    if (aluno.getId() == null) {
      aluno.setId(alunos.size() + 1);
    }
    alunos.add(aluno);
    return ResponseEntity.status(HttpStatus.CREATED).body(aluno.getId());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Aluno> findById(@PathVariable("id") Integer id) {
    Optional<Aluno> alunoOptional =
        alunos.stream().filter(aluno -> aluno.getId().equals(id)).findFirst();
    if (alunoOptional.isPresent()) {
      Aluno aluno = alunoOptional.get();
      return ResponseEntity.ok(aluno);
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody final Aluno aluno) {
    alunos.stream()
        .filter(a -> a.getId().equals(id))
        .forEach(
            a -> {
              a.setNome(aluno.getNome());
              a.setIdade(aluno.getIdade());
            });
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Integer id) {
    alunos.removeIf(aluno -> aluno.getId().equals(id));
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
