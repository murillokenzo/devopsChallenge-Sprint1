package com.fiap.nuclear;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aplicacoes")
public class AplicacaoController {

    private final AplicacaoRepository repo;

    public AplicacaoController(AplicacaoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<AplicacaoNuclear> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AplicacaoNuclear> buscar(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AplicacaoNuclear criar(@Valid @RequestBody AplicacaoNuclear a) {
        a.setId(null);
        return repo.save(a);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AplicacaoNuclear> atualizar(@PathVariable Long id,
                                                      @Valid @RequestBody AplicacaoNuclear a) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        a.setId(id);
        return ResponseEntity.ok(repo.save(a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
