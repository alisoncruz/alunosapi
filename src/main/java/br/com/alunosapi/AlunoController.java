package br.com.alunosapi;

import br.com.alunosapi.model.Aluno;
import br.com.alunosapi.service.AlunoService;
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

  private AlunoService alunoService;

  public AlunoController() {
    this.alunoService = new AlunoService();
  }

  @GetMapping
  public ResponseEntity<List<Aluno>> findAll(
      @RequestParam(required = false) String nome, @RequestParam(required = false) Integer idade) {
    return alunoService.findAll(nome, idade);
  }

  @PostMapping
  public ResponseEntity<Integer> add(@RequestBody final Aluno aluno) {
    return alunoService.add(aluno);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Aluno> findById(@PathVariable("id") final Integer id) {
    return alunoService.findById(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody final Aluno aluno) {
    return alunoService.update(id, aluno);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable final Integer id) {
    return alunoService.delete(id);
  }

  @GetMapping("/nome/{nome}")
  public ResponseEntity<Aluno> getByNome(@PathVariable("nome") final String nome) {
    return alunoService.findByNome(nome);
  }
}
