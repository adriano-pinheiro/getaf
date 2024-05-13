package br.com.fiap.adj.techchallenge.getaf.controller;

import br.com.fiap.adj.techchallenge.getaf.dto.ContratoDTO;
import br.com.fiap.adj.techchallenge.getaf.repository.ContratoRepository;
import br.com.fiap.adj.techchallenge.getaf.service.ContratoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contrato")
public class ContratoController {

    private final ContratoService contratoService;
    private final ContratoRepository repository;

    @Autowired
    public ContratoController(ContratoService contratoService, ContratoRepository repository) {
        this.contratoService = contratoService;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Page<ContratoDTO>> findAll(
            @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable
    ) {
        Page<ContratoDTO> contratoDTOS = contratoService.findAll(pageable);
        return ResponseEntity.ok(contratoDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> findById(@PathVariable Long id) {
        ContratoDTO contratoDTO = contratoService.findById(id);
        return ResponseEntity.ok(contratoDTO);
    }

    @PostMapping
    public ResponseEntity<ContratoDTO> save(@Valid @RequestBody ContratoDTO contratoDTO) {
        ContratoDTO savedContratoDTO = contratoService.save(contratoDTO);
        return new ResponseEntity<>(savedContratoDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratoDTO> update(@PathVariable Long id, @RequestBody ContratoDTO contratoDTO) {
        ContratoDTO updatedContratoDTO = contratoService.update(id, contratoDTO);
        return ResponseEntity.ok(updatedContratoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contratoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
