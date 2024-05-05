package br.com.fiap.adj.techchallenge.getaf.repository;

import br.com.fiap.adj.techchallenge.getaf.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByLogin(String login);
}