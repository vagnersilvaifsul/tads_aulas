package br.edu.ifsul.cstsi.tads_aulas.produto;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDtoResponse>> findAll(){
        return ResponseEntity.ok(repository.findAll().stream().map(ProdutoDtoResponse::new).toList());
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

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<URI> insert(@RequestBody ProdutoDTOPost produtoDTOPost, UriComponentsBuilder uriBuilder){
        var p = repository.save(new Produto(
                produtoDTOPost.nome(),
                produtoDTOPost.valorDeCompra(),
                produtoDTOPost.valorDeVenda(),
                produtoDTOPost.descricao(),
                produtoDTOPost.estoque()
        ));
        var location = uriBuilder.path("api/v1/produtos/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoDtoResponse> update(@PathVariable("id") Long id, @Valid @RequestBody ProdutoDTOPut produtoDTOPut){
        var p = repository.save(new Produto(
                id,
                produtoDTOPut.nome(),
                produtoDTOPut.valorDeCompra(),
                produtoDTOPut.valorDeVenda(),
                produtoDTOPut.descricao(),
                produtoDTOPut.situacao(),
                produtoDTOPut.estoque()
        ));
        return p != null ?
                ResponseEntity.ok(new ProdutoDtoResponse(p)):
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
