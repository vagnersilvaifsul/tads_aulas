package br.edu.ifsul.cstsi.tads_aulas.api.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {
    @GetMapping
    public ResponseEntity<String> selectAll() {
        return ResponseEntity.ok("selectAll()");
    }

    @GetMapping("{id}") //api/v1/produtos/1
    public ResponseEntity<String> selectById(@PathVariable("id") Long id) {
        return ResponseEntity.ok("selectById() " + id);
    }

    @GetMapping("/nome/{nome}") //api/v1/produtos/nome/arroz
    public ResponseEntity<String> selectByName(@PathVariable("nome") String nome) {
        return ResponseEntity.ok("selectByName() " + nome);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Produto produto){
        return ResponseEntity.ok("insert() " + produto);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Produto produto){
        return ResponseEntity.ok("update() " + id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok("delete() " + id);
    }

}
