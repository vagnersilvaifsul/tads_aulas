package br.edu.ifsul.cstsi.tads_aulas.produto;

import br.edu.ifsul.cstsi.tads_aulas.BaseAPIIntegracaoTest;
import br.edu.ifsul.cstsi.tads_aulas.TadsAulasApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TadsAulasApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ProdutoControllerIntegracaoTest extends BaseAPIIntegracaoTest {

    //Métodos utilitários (eles encapsulam o TestRestTemplate e eliminam a repetição de código nos casos de teste)
    private ResponseEntity<ProdutoDtoResponse> getProduto(String url) {
        return get(url, ProdutoDtoResponse.class);
    }

    private ResponseEntity<List<ProdutoDtoResponse>> getProdutosList(String url) {
        var headers = getHeaders();

        return rest.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                });
    }

    @Test
    void findAll() {
        //Arrange //Act
        var data = getProdutosList("/api/v1/produtos").getBody();

        //Assert
        assertNotNull(data);
        assertEquals(5, data.size());

        //Arrange //Act
        var pageData = getProdutosList("/api/v1/produtos?page=0&size=5").getBody();

        //Assert
        assertNotNull(pageData);
        assertEquals(5, pageData.size());

    }

    @Test
    void findById() {
    }

    @Test
    void findByNome() {
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