package br.edu.ifsul.cstsi.tads_aulas.api.autenticacao;

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

    private AuthenticationManager manager;

    public AutenticacaoController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @PostMapping("api/v1/login")
    public ResponseEntity<String> efetuarLogin(@RequestBody UsuarioAutenticacaoDTO data){
        var authenticationDTO = new UsernamePasswordAuthenticationToken(data.usuario(), data.senha());
        var authentication = manager.authenticate(authenticationDTO);
        return ResponseEntity.ok("Autenticou");
    }
}
