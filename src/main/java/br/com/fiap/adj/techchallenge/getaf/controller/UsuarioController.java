package br.com.fiap.adj.techchallenge.getaf.controller;

import br.com.fiap.adj.techchallenge.getaf.dto.UsuarioDTO;
import br.com.fiap.adj.techchallenge.getaf.repository.UsuarioRepository;
import br.com.fiap.adj.techchallenge.getaf.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository repository;
    private BCryptPasswordEncoder criptografia;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, UsuarioRepository repository) {
        this.usuarioService = usuarioService;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> findAll(
            @PageableDefault(page = 0, size = 10, sort = "nome") Pageable pageable
    ) {
        Page<UsuarioDTO> usuarioDTOS = usuarioService.findAll(pageable);
        return ResponseEntity.ok(usuarioDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.findById(id);
        return ResponseEntity.ok(usuarioDTO);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO savedUsuarioDTO = usuarioService.save(usuarioDTO);
        return new ResponseEntity<>(savedUsuarioDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO updatedUsuarioDTO = usuarioService.update(id, usuarioDTO);
        return ResponseEntity.ok(updatedUsuarioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/validarSenha")
//    public ResponseEntity<Boolean> validarSenha(@RequestParam String login,
//                                                @RequestParam String password) {
//
//        Optional<Usuario> optUsuario = repository.findByLogin(login);
//        if (optUsuario.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
//        }
//
//        Usuario usuario = optUsuario.get();
//        boolean valid = criptografia.matches(password, usuario.getPassword());
//
//        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
//        return ResponseEntity.status(status).body(valid);
//
//    }

}
