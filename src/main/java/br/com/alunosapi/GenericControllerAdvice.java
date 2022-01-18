package br.com.alunosapi;

import br.com.alunosapi.exception.AlunoNaoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericControllerAdvice {

  private Logger LOGGER = LoggerFactory.getLogger(GenericControllerAdvice.class);

  @ExceptionHandler({AlunoNaoEncontradoException.class})
  public ResponseEntity<String> alunoNaoEncontradoExceptionHandler(
      final AlunoNaoEncontradoException alunoNaoEncontradoException) {
    LOGGER.info("Aluno não encontrado");
    return new ResponseEntity<>(alunoNaoEncontradoException.getMessage(), HttpStatus.NOT_FOUND);
  }
}
