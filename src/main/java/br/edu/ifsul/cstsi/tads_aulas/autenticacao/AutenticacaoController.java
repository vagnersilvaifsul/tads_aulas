package br.edu.ifsul.cstsi.tads_aulas.autenticacao;

import br.edu.ifsul.cstsi.tads_aulas.infra.security.TokenJwtDTO;
import br.edu.ifsul.cstsi.tads_aulas.infra.security.TokenService;
import br.edu.ifsul.cstsi.tads_aulas.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//indica que essa classe deve ser adicionada ao Contexto do aplicativo como um Bean da camada de controle API REST
@RequestMapping("api/v1/login") //Endpoint padrão da classe
public class AutenticacaoController {

    private final AuthenticationManager manager; //o gerenciador de autenticação é quem dispara o loadUserByUsername (isto é, é interno do Spring Security, tem que usar ele)
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenJwtDTO> efetuaLogin(@RequestBody UsuarioAutenticacaoDTO data) {
        var authenticationDTO = new UsernamePasswordAuthenticationToken(data.email(), data.senha()); //converte o DTO em DTO do Spring Security

        var authentication = manager.authenticate(authenticationDTO); //autentica o usuário (esse objeto contém o usuário e a senha)
        var tokenJWT = tokenService.geraToken((Usuario) authentication.getPrincipal()); //gera o token JWT para enviar na response
        return ResponseEntity.ok(new TokenJwtDTO(tokenJWT)); //envia a response com o token JWT
    }
}
