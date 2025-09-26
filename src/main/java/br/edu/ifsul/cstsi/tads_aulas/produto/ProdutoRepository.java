package br.edu.ifsul.cstsi.tads_aulas.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(exported = false)
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    //@Query(value = "SELECT p FROM Produto p where p.nome like ?1 order by p.nome")
    @Query(value = "select * from produtos p where p.nome like ?1 order by p.nome", nativeQuery=true)
    List<Produto> findByNome(String nome);
}
