package br.edu.ifsul.cstsi.tads_aulas.usuario;

/**
 * DTO for {@link Usuario}
 */
public record UsuarioDTO(
    String nome,
    String sobrenome,
    String email,
    String senha,
    String perfil) {
}
