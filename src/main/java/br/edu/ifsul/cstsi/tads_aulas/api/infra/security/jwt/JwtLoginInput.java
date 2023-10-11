package br.edu.ifsul.cstsi.tads_aulas.api.infra.security.jwt;

import lombok.Data;

@Data
public class JwtLoginInput {
    private String username;
    private String password;
}