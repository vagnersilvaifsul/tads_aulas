package br.edu.ifsul.cstsi.tads_aulas.api.autenticacao;

import br.edu.ifsul.cstsi.tads_aulas.api.infra.security.TokenJwtDTO;
import br.edu.ifsul.cstsi.tads_aulas.api.infra.security.TokenService;
import br.edu.ifsul.cstsi.tads_aulas.api.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping("api/v1/login")
    public ResponseEntity<TokenJwtDTO> efetuarLogin(@RequestBody UsuarioAutenticacaoDTO data){
        var authenticationDTO = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var authentication = manager.authenticate(authenticationDTO);
        var tokenJWT = tokenService.geraToken((Usuario) authentication.getPrincipal()); //gera o token JWT para enviar na response
        return ResponseEntity.ok(new TokenJwtDTO(tokenJWT)); //envia a response com o token JWT
    }
}
