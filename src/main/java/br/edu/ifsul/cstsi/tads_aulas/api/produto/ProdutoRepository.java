package br.edu.ifsul.cstsi.tads_aulas.api.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    @Query(value = "SELECT p FROM produtos p where p.nome like ?1")
    List<Produto> findByNome(String nome);
}