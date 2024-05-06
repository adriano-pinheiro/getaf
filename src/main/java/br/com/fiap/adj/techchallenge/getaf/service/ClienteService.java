package br.com.fiap.adj.techchallenge.getaf.service;

import br.com.fiap.adj.techchallenge.getaf.controller.exception.ControllerNotFoundException;
import br.com.fiap.adj.techchallenge.getaf.dto.ClienteDTO;
import br.com.fiap.adj.techchallenge.getaf.dto.UsuarioDTO;
import br.com.fiap.adj.techchallenge.getaf.model.Cliente;
import br.com.fiap.adj.techchallenge.getaf.model.Usuario;
import br.com.fiap.adj.techchallenge.getaf.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Page<ClienteDTO> findAll(Pageable pageable){
        Page<Cliente> clientes = repository.findAll(pageable);
        return clientes.map(this::toDTO);
    }

    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = repository.save(toEntity(clienteDTO));
        return toDTO(cliente);
    }

    public ClienteDTO findById(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow( () ->
                        new ControllerNotFoundException("Cliente não encontrado."));
        return toDTO(cliente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = repository.findById(id)
                .orElseThrow( () ->
                        new ControllerNotFoundException("Cliente não encontrado."));

        cliente.setNome(clienteDTO.nome());
        cliente.setEmail(clienteDTO.email());
        cliente.setTelefone(clienteDTO.telefone());
        cliente.setCnpj(clienteDTO.cnpj());
        cliente.setBairro(clienteDTO.bairro());
        cliente.setCidade(clienteDTO.cidade());
        cliente.setEndereco(clienteDTO.endereco());
        cliente.setCep(clienteDTO.cep());
        cliente.setUf(clienteDTO.uf());
        return toDTO(cliente);
    }

    public ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCnpj(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEndereco(),
                cliente.getComplemento(),
                cliente.getCep(),
                cliente.getBairro(),
                cliente.getCidade(),
                cliente.getUf()
        );
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        return new Cliente(
                clienteDTO.id(),
                clienteDTO.nome(),
                clienteDTO.cnpj(),
                clienteDTO.email(),
                clienteDTO.telefone(),
                clienteDTO.endereco(),
                clienteDTO.complemento(),
                clienteDTO.cep(),
                clienteDTO.bairro(),
                clienteDTO.cidade(),
                clienteDTO.uf()
        );
    }
}
