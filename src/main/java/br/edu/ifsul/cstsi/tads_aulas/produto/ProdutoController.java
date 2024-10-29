package br.edu.ifsul.cstsi.tads_aulas.produto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    @Autowired //TODO: Mostrar como injetar corretamente as dependências no Controller
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll(){
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("{id}") //URL_BASE:8080/api/v1/produtos/1
    public String getById(@PathVariable("id") Long id){
        return "Produto de id=" + id;
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> selectByNome(@PathVariable("nome") String nome) {
        var produtos = produtoRepository.findByNome(nome);
        return produtos.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<URI> insert(@RequestBody Produto produto, UriComponentsBuilder uriBuilder){
        produto.setId(null);
        var p = produtoRepository.save(produto);
        var location = uriBuilder.path("api/v1/produtos/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Produto> update(@PathVariable("id") Long id, @RequestBody Produto produto){
        var optional = produtoRepository.findById(id);
        if(optional.isPresent()){
            var p = produtoRepository.save(produto);
            return ResponseEntity.ok(p);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        var optional = produtoRepository.findById(id);
        if(optional.isPresent()){
            produtoRepository.deleteById(id);
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
