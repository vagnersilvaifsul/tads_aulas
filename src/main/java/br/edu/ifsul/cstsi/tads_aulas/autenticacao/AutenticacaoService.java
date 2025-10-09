package br.edu.ifsul.cstsi.tads_aulas.autenticacao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //indica que essa classe deve ser adicionada ao Contexto do aplicativo como um Bean da camada de serviço de dados
public class AutenticacaoService implements UserDetailsService {
    private final AutenticacaoRepository rep;

    public AutenticacaoService(AutenticacaoRepository rep) {
        this.rep = rep;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return rep.findByEmail(username);
    }
}
