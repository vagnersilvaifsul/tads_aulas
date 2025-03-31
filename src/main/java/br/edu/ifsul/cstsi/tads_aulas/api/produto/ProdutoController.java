package br.edu.ifsul.cstsi.tads_aulas.api.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {


    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> getProdutos() {
        var produtos = produtoRepository.findAll();
        return ResponseEntity
                .ok()
                .body(
                        produtos.stream()
                                .map(p ->
                                        new ProdutoDto(
                                                p.getId(),
                                                p.getNome(),
                                                p.getValorDeVenda(),
                                                p.getDescricao(),
                                                p.getEstoque())).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable("id") Long id) {
        var produto = produtoRepository.findById(id);
        return produto.isEmpty() ? ResponseEntity.notFound().build() :  ResponseEntity.ok().body(produto.get()) ;
    }

    @GetMapping("nome/{nome}")
    public String getProdutoByNome(@PathVariable("nome") String nome) {
        return "Produto com nome: " + nome;
    }
}
