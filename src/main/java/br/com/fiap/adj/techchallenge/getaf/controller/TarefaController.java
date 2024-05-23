package br.com.fiap.adj.techchallenge.getaf.controller;

import br.com.fiap.adj.techchallenge.getaf.dto.TarefaDTO;
import br.com.fiap.adj.techchallenge.getaf.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tarefa")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService tarefaService) {
        this.service = tarefaService;
    }

    @GetMapping
    public ResponseEntity<Page<TarefaDTO>> findAll(
            @PageableDefault(page = 0, size = 10, sort = "nome") Pageable pageable
    ) {
        Page<TarefaDTO> Tarefas = service.findAll(pageable);
        return ResponseEntity.ok(Tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> findById(@PathVariable Long id) {
        TarefaDTO Tarefa = service.findById(id);
        return ResponseEntity.ok(Tarefa);
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> save(@Valid @RequestBody TarefaDTO TarefaDTO) {
        TarefaDTO Tarefa = service.save(TarefaDTO);
        return new ResponseEntity<>(Tarefa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> update(@PathVariable Long id, @RequestBody TarefaDTO TarefaDTO) {
        TarefaDTO Tarefa = service.update(id, TarefaDTO);
        return ResponseEntity.ok(Tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
