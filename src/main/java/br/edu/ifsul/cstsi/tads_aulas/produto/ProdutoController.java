package br.edu.ifsul.cstsi.tads_aulas.produto;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> findAll() {
        return ResponseEntity.ok(produtoRepository.findAll().stream().map(ProdutoDto::new).toList());
    }

    @GetMapping("{id}") //URL_BASE:8080/api/v1/produtos/1
    public ResponseEntity<ProdutoDto> findById(@PathVariable("id") Long id) {
        var optional = produtoRepository.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(new ProdutoDto(optional.get()));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProdutoDto>> finByNome(@PathVariable("nome") String nome) {
        var produtos = produtoRepository.findByNome(nome);
        return produtos.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(produtos.stream().map(ProdutoDto::new).toList());
    }

    @PostMapping
    @Secured("ADMIN")
    public ResponseEntity<URI> insert(@RequestBody ProdutoDto produtoDTO, UriComponentsBuilder uriBuilder) {
        var p = produtoRepository.save(new Produto(
                null,
                produtoDTO.nome(),
                produtoDTO.valorDeCompra(),
                produtoDTO.valorDeVenda(),
                produtoDTO.descricao(),
                produtoDTO.situacao(),
                produtoDTO.estoque(),
                null
        ));
        var location = uriBuilder.path("api/v1/produtos/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoDto> update(@PathVariable("id") Long id, @RequestBody ProdutoDto produtoDTO) {
        var optional = produtoRepository.findById(id);
        if (optional.isPresent()) {
            var p = produtoRepository.save(new Produto(
                    id,
                    produtoDTO.nome(),
                    produtoDTO.valorDeCompra(),
                    produtoDTO.valorDeVenda(),
                    produtoDTO.descricao(),
                    produtoDTO.situacao(),
                    produtoDTO.estoque(),
                    null
            ));
            return ResponseEntity.ok(new ProdutoDto(p));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProdutoDto> delete(@PathVariable("id") Long id) {
        var optional = produtoRepository.findById(id);
        if (optional.isPresent()) {
            produtoRepository.deleteById(id);
            return ResponseEntity.ok(new ProdutoDto(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
