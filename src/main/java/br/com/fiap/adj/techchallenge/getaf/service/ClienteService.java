package br.com.fiap.adj.techchallenge.getaf.service;

import br.com.fiap.adj.techchallenge.getaf.controller.exception.ControllerNotFoundException;
import br.com.fiap.adj.techchallenge.getaf.dto.ClienteDTO;
import br.com.fiap.adj.techchallenge.getaf.dto.EnderecoDTO;
import br.com.fiap.adj.techchallenge.getaf.model.Cliente;
import br.com.fiap.adj.techchallenge.getaf.model.Endereco;
import br.com.fiap.adj.techchallenge.getaf.repository.ClienteRepository;
import br.com.fiap.adj.techchallenge.getaf.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Page<ClienteDTO> findAll(Pageable pageable){
        Page<Cliente> clientes = repository.findAll(pageable);
        return clientes.map(this::toDTO);
    }

    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = repository.save(toEntity(clienteDTO));
        if (cliente.getId() != null) {
            for (Endereco endereco : cliente.getEnderecos()) {
                endereco.setCliente(cliente);
                enderecoRepository.save(endereco);
            }
        }
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
        return toDTO(cliente);
    }

    public ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCnpj(),
                cliente.getEmail(),
                cliente.getTelefone(),
                toEnderecoDTO(cliente.getEnderecos())
        );
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        return new Cliente(
                clienteDTO.id(),
                clienteDTO.nome(),
                clienteDTO.cnpj(),
                clienteDTO.email(),
                clienteDTO.telefone(),
                toEndereco(clienteDTO.enderecos())
        );
    }

    public List<Endereco> toEndereco(List<EnderecoDTO> enderecosDTO) {
        List<Endereco> enderecos = new ArrayList<>();

        for (EnderecoDTO enderecoDto : enderecosDTO) {
            Endereco endereco = new Endereco();
            endereco.setId(enderecoDto.id());
            endereco.setLogradouro(enderecoDto.logradouro());
            endereco.setNumero(enderecoDto.numero());
            endereco.setBairro(enderecoDto.bairro());
            endereco.setCidade(enderecoDto.cidade());
            endereco.setCep(enderecoDto.cep());

            enderecos.add(endereco);
        }
        return enderecos;
    }

    public List<EnderecoDTO> toEnderecoDTO(List<Endereco> enderecos) {
        List<EnderecoDTO> enderecoDTOs = new ArrayList<>();

        for (Endereco endereco : enderecos) {
            EnderecoDTO enderecoDTO = new EnderecoDTO(
                    endereco.getId(),
                    endereco.getLogradouro(),
                    endereco.getNumero(),
                    endereco.getComplemento(),
                    endereco.getBairro(),
                    endereco.getCidade(),
                    endereco.getCep()
            );
            enderecoDTOs.add(enderecoDTO);
        }
        return enderecoDTOs;
    }
}
