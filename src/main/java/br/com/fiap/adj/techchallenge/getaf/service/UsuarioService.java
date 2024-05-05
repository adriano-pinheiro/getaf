package br.com.fiap.adj.techchallenge.getaf.service;

import br.com.fiap.adj.techchallenge.getaf.controller.exception.ControllerNotFoundException;
import br.com.fiap.adj.techchallenge.getaf.dto.UsuarioDTO;
import br.com.fiap.adj.techchallenge.getaf.model.Usuario;
import br.com.fiap.adj.techchallenge.getaf.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder criptografia;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Page<UsuarioDTO> findAll(Pageable pageable){
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return  usuarios.map(this::toDTO);
    }

    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ControllerNotFoundException("Usuario não encontrado"));
        return toDTO(usuario);
    }

    public  UsuarioDTO save(UsuarioDTO usuarioDTO) {
        final PasswordEncoder encoder = null;

        Usuario usuario = usuarioRepository.save(toEntity(usuarioDTO));
        //Criptografando a senha
        String password =  criptografia.encode(usuarioDTO.password());
        usuario.setPassword(password);

        usuario = usuarioRepository.save(usuario);
        return toDTO(usuario);
    }

    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario =  usuarioRepository.getOne(id);
            usuario.setNome(usuarioDTO.nome());
            usuario.setLogin(usuarioDTO.login());
            usuario.setCpf(usuarioDTO.cpf());
            usuario.setDataNascimento(usuarioDTO.dataNascimento());
            usuario.setEmail(usuarioDTO.email());
            usuario.setPassword(criptografia.encode(usuarioDTO.password()));
            usuario = usuarioRepository.save(usuario);
            return toDTO(usuario);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuario não encontrado");
        }
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getDataNascimento(),
                usuario.getPassword()
        );
    }

    private Usuario toEntity(UsuarioDTO usuarioDTO) {
        return new Usuario(
                usuarioDTO.id(),
                usuarioDTO.nome(),
                usuarioDTO.login(),
                usuarioDTO.email(),
                usuarioDTO.cpf(),
                usuarioDTO.dataNascimento(),
                usuarioDTO.password()
        );
    }

}
