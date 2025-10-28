package com.example.apirest.controller;

import com.example.apirest.domain.Produto;
import com.example.apirest.dto.ProdutoRequest;
import com.example.apirest.dto.ProdutoResponse;
import com.example.apirest.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoResponse> criar(@Valid @RequestBody ProdutoRequest body) {
        Produto p = new Produto();
        p.setNome(body.getNome());
        p.setPreco(body.getPreco());
        p.setEstoque(body.getEstoque());
        p.setDescricao(body.getDescricao());
        p = repository.save(p);
        ProdutoResponse r = toResponse(p);
        return ResponseEntity.created(URI.create("/produtos/" + p.getId())).body(r);
    }

    @GetMapping
    public List<ProdutoResponse> listar() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscar(@PathVariable Long id) {
        return repository.findById(id).map(p -> ResponseEntity.ok(toResponse(p))).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequest body) {
        return repository.findById(id).map(p -> {
            p.setNome(body.getNome());
            p.setPreco(body.getPreco());
            p.setEstoque(body.getEstoque());
            p.setDescricao(body.getDescricao());
            Produto saved = repository.save(p);
            return ResponseEntity.ok(toResponse(saved));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ProdutoResponse toResponse(Produto p) {
        ProdutoResponse r = new ProdutoResponse();
        r.setId(p.getId());
        r.setNome(p.getNome());
        r.setPreco(p.getPreco());
        r.setEstoque(p.getEstoque());
        r.setDescricao(p.getDescricao());
        r.setCriadoEm(p.getCriadoEm());
        return r;
    }
}
