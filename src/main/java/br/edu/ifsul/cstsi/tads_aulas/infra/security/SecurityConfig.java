package br.edu.ifsul.cstsi.tads_aulas.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean //indica ao spring boot que essa configuração deve ser adicionada ao contexto do aplicativo (objeto injetado em AutenticacaoController)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean //indica ao spring boot que essa configuração deve ser adicionada ao contexto do aplicativo (utilizado pelo Spring Boot para o decode da senha)
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(); //devolve o BCryptPasswordEncoder como codificador e decodificador de senhas
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Basic Authentication
        http
            .csrf(csrf -> csrf.disable()) //desabilita a proteção contra ataques Cross-site Request Forger
            .authorizeHttpRequests(authorize -> {
                authorize.requestMatchers(HttpMethod.POST, "/api/v1/login").permitAll(); //exceto, a rota de login
                authorize.anyRequest().authenticated(); //demais rotas devem ser autenticadas
            })
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

//    @Bean //indica ao spring boot que essa configuração deve ser adicionada ao contexto do aplicativo
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.withUsername("user")
//            .password(passwordEncoder.encode("user"))
//            .roles("USER")
//            .build();
//
//        UserDetails admin = User.withUsername("admin")
//            .password(passwordEncoder.encode("admin"))
//            .roles("USER", "ADMIN")
//            .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

}
