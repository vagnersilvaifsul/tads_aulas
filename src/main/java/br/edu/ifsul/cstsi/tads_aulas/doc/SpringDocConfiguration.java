package br.edu.ifsul.cstsi.tads_aulas.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //indica que essa classe deve ser adicionada ao Contexto do aplicativo como um Bean de Configuração da Documentação da API
public class SpringDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Desenvolvida em sala de aula na disciplina de TDS")
                .description("API Rest da aplicação tads_aulas, contendo as funcionalidades de CRUD de produtos.")
                .contact(new Contact()
                    .name("Time Aulas TDS")
                    .email("tds@ifsul.edu.br"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://localhost:8080/api/licenca")));
    }
}
