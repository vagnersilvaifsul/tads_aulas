package br.edu.ifsul.cstsi.tads_aulas.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource(exported = false) //Esta anotação desliga a geração automática de controller pelo Spring Rest
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    //método em Query Speak (em JPQL ou SQL)
    //método em Domain Speak (só pelo nome do método, o SQL é gerado pelo Spring Data a partir da assinatura do método.)
    @Query("SELECT p FROM Produto p WHERE p.nome LIKE :nome%")
    Optional<List<Produto>> findByNome(String nome);
}