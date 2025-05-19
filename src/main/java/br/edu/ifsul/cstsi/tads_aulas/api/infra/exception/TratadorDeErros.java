package br.edu.ifsul.cstsi.tads_aulas.api.infra.exception;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(TokenInvalidoException.class)
    public ResponseEntity<ErroDTO> tratarErroTokenInvalido(TokenInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErroDTO("Token inválido", ex.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacaoDTO>> tratarErroValidacao(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors().stream().map(ErroValidacaoDTO::new).toList();
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErroDTO> tratarErroValidacao(ValidationException ex) {
        return ResponseEntity.badRequest().body(
                new ErroDTO("Erro de validação", ex.getMessage())
        );
    }

    private record ErroDTO(String erro, String mensagem) {
    }

    private record ErroValidacaoDTO(String campo, String mensagem) {
        public ErroValidacaoDTO(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}