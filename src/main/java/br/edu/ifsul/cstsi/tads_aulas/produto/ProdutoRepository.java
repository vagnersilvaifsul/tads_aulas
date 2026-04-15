package br.edu.ifsul.cstsi.tads_aulas.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource(exported = false) //Esta anotação desliga a geração automática de controller pelo Spring Rest
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    /*
        Para exemplificar eu coloquei a mesma busca em Domain Speak e Query Speak. Assim vocês podem contrastar
        as três diferentes maneiras de buscar dados no banco de dados com Spring Data.
     */

    Optional<List<Produto>> findByNomeStartingWith(String nome);

    //Método em Query Speak (em JPQL). Note o nome da entidade Produto no JPQL, isso mostra que ele roda no JPA.
    @Query("SELECT p FROM Produto p WHERE p.nome LIKE :nome%")
    Optional<List<Produto>> findByNomeQuerySpeakJPQL(String nome);

    //Método em Query Speak (em SQL)
    @Query(value = "SELECT p.* FROM produtos p WHERE p.nome LIKE CONCAT(?1, '%') ORDER BY p.nome", nativeQuery=true)
    Optional<List<Produto>> findByNomeQuerySpeakSQL(String nome);
}