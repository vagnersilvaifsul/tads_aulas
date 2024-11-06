package br.edu.ifsul.cstsi.tads_aulas.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //Faz o SB inserir um objeto no Contexto do app
@EnableWebSecurity //O que está sendo configurado é a segurança do app
@EnableMethodSecurity(securedEnabled = true) //nível de granularidade de autorização a recursos
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean //essa anotação manda inserir o objeto da classe AuthenticationManager no Context do app
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Configuração JWT Authentication
        http
                .csrf(AbstractHttpConfigurer::disable) //desabilita a proteção contra ataques Cross-site Request Forger, comum em conexões Stateless
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //sem sessão (desabilita o stateful)
                .authorizeHttpRequests(authorize -> {  //configurar a autorização
                    authorize.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll(); //exceto, a rota de documentação (para doc em html no navegador; e para ferramentas automatizadas de geração de código)
                    authorize.requestMatchers(HttpMethod.POST, "/api/v1/login", "/api/v1/usuarios/cadastrar").permitAll(); //exceto, a rota de login e de cadastro de usuário
                    authorize.requestMatchers(HttpMethod.GET, "/confirmar-email").permitAll(); //exceto, a rota de confirmação de email
                    authorize.anyRequest().authenticated(); //demais rotas devem ser autenticadas
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class); //manda o filter do projeto vir antes do filter do Spring

        return http.build();
    }

}
