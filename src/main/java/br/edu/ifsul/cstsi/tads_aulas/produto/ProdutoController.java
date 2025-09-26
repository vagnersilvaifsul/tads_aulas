package br.edu.ifsul.cstsi.tads_aulas.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        var optionalProduto = repository.findById(id);
        if(optionalProduto.isPresent()){
            return ResponseEntity.ok(optionalProduto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<List<Produto>> findByNome(@PathVariable  String nome){
        var produtos = repository.findByNome(nome + "%");
        if(produtos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtos);
    }
}
