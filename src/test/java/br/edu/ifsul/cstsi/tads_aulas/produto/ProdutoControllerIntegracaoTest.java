package br.edu.ifsul.cstsi.tads_aulas.produto;

import br.edu.ifsul.cstsi.tads_aulas.BaseAPIIntegracaoTest;
import br.edu.ifsul.cstsi.tads_aulas.TadsAulasApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = TadsAulasApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//carrega o Context do app em um container Spring Boot com um servidor web
@ActiveProfiles("test") //indica o profile que o Spring Boot deve utilizar para passar os testes
class ProdutoControllerIntegracaoTest extends BaseAPIIntegracaoTest {

    //Métodos utilitários (eles encapsulam o TestRestTemplate e eliminam a repetição de código nos casos de teste)
    private ResponseEntity<ProdutoDto> getProduto(String url) {
        return get(url, ProdutoDto.class);
    }

    private ResponseEntity<List<ProdutoDto>> getProdutosList(String url) {
        var headers = getHeaders();

        return rest.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                });
    }

    @Test
    @DisplayName("Espera uma lista com 5 objetos")
    void findAll() {
        //ACT
        var produtos = getProdutosList("/api/v1/produtos").getBody();

        //ASSERT
        assertNotNull(produtos);
        assertEquals(5, produtos.size());
    }

    @Test
    void findById() {
    }

    @Test
    void finByNome() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}