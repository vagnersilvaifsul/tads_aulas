package br.edu.ifsul.cstsi.tads_aulas.api.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping //api/v1/produtos
    public ResponseEntity<List<ProdutoDTO>> selectAll() {
        return ResponseEntity.ok(service.getProdutos());
    }

    @GetMapping("{id}") //api/v1/produtos/1
    public ResponseEntity<ProdutoDTO> selectById(@PathVariable("id") Long id) {
        ProdutoDTO p = service.getProdutoById(id);
        return p != null ?
            ResponseEntity.ok(p) :
            ResponseEntity.notFound().build();
    }

    @GetMapping("/nome/{nome}") //api/v1/produtos/nome/arroz
    public ResponseEntity<List<ProdutoDTO>> selectByName(@PathVariable("nome") String nome) {
        List<ProdutoDTO> produtos = service.getProdutosByNome(nome);
        return produtos.isEmpty() ?
            ResponseEntity.noContent().build() :
            ResponseEntity.ok(produtos);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping //api/v1/produtos
    public ResponseEntity<String> insert(@RequestBody Produto produto){
        ProdutoDTO p = service.insert(produto);
        URI location = getUri(p.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}") //api/v1/produtos/1
    public ResponseEntity<ProdutoDTO> update(@PathVariable("id") Long id, @RequestBody Produto produto){
        produto.setId(id);
        ProdutoDTO p = service.update(produto, id);
        return p != null ?
            ResponseEntity.ok(p) :
            ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}") //api/v1/produtos/1
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return service.delete(id) ?
            ResponseEntity.ok().build() :
            ResponseEntity.notFound().build();
    }

    //utilitário
    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
