package br.edu.ifsul.cstsi.tads_aulas.api.autenticacao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioAutenticacaoDTO(
        @NotBlank(message = "O email não pode ser nulo ou vazio")
        @Email(message = "(minha) O email deve conter um @ e um . , no mínimo")
        String email,
        @NotBlank (message = "(minha) A senha não pode ser nula ou vazia")
        String senha
) {
}
