package br.edu.ifsul.cstsi.tads_aulas.autenticacao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioAutenticacaoDTO(
        @Email(message = "O email deve ter @ e  . , no mínimo.")
        @NotBlank(message = "O campo de Email não pode ser vazio ou nulo")
        String email,
        @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$", message = "A senha deve conter ao menos uma letra maiúscula, uma letra minúscula, um númeral, um caractere especial e um total de 8 caracteres.")
        @NotBlank(message = "A senha não pode ser nula ou vazia")
        String senha) {
}
