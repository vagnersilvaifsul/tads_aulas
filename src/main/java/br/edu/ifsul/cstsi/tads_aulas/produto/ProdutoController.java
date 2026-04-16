package br.edu.ifsul.cstsi.tads_aulas.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    // Injeção das dependências da classe ProdutoController.
    // Em outras palavras, quais são as dependências necessárias para a classe ProdutoController funcionar
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    @GetMapping
    public ResponseEntity<List<ProdutoDto>> findAll(){
        return ResponseEntity.ok(
                produtoRepository.findAll().stream() //1. criar um fluxo
                        .map(ProdutoDto::new) //2. Realizar operações (neste caso, foi feito um mapeamento de um tipo para outro)
                        .toList() //3. coleta a informação para uma estrutura de dados (no caso, List)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produto.get());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> findByName(@PathVariable String nome){
        Optional<List<Produto>> produtos = produtoRepository.findByNomeStartingWith(nome);
        if(produtos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtos.get());
    }

    @PostMapping
    public ResponseEntity<URI> save(@RequestBody ProdutoDtoPost produto, UriComponentsBuilder uriBuilder){
        var p = produtoRepository.save(new Produto(
                null,
                produto.nome(),
                produto.valorDeCompra(),
                produto.valorDeVenda(),
                produto.descricao(),
                true,
                produto.estoque()
        ));
        var location = uriBuilder.path("api/v1/produtos/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto){
        produto.setId(id);
        var p = produtoRepository.save(produto);
        return p != null ?
                ResponseEntity.ok(p) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
