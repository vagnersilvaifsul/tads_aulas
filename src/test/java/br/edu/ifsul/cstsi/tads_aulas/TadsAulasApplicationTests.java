package br.edu.ifsul.cstsi.tads_aulas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") //indica o profile que o Spring Boot deve utilizar para passar os testes
class TadsAulasApplicationTests {

    @Test
    void contextLoads() {
    }

}
