package br.com.fiap.adj.techchallenge.getaf.service;

import br.com.fiap.adj.techchallenge.getaf.controller.exception.ControllerNotFoundException;
import br.com.fiap.adj.techchallenge.getaf.dto.TarefaDTO;
import br.com.fiap.adj.techchallenge.getaf.dto.UsuarioDTO;
import br.com.fiap.adj.techchallenge.getaf.model.Tarefa;
import br.com.fiap.adj.techchallenge.getaf.model.Usuario;
import br.com.fiap.adj.techchallenge.getaf.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public Page<TarefaDTO> findAll(Pageable pageable){
        Page<Tarefa> Tarefas = repository.findAll(pageable);
        return Tarefas.map(this::toDTO);
    }

    public TarefaDTO save(TarefaDTO TarefaDTO) {
        Tarefa Tarefa = repository.save(toEntity(TarefaDTO));
        return toDTO(Tarefa);
    }

    public TarefaDTO findById(Long id) {
        Tarefa Tarefa = repository.findById(id)
                .orElseThrow( () ->
                        new ControllerNotFoundException("Tarefa não encontrado."));
        return toDTO(Tarefa);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public TarefaDTO update(Long id, TarefaDTO TarefaDTO) {
        Tarefa Tarefa = repository.findById(id)
                .orElseThrow( () ->
                        new ControllerNotFoundException("Tarefa não encontrado."));
        
        Tarefa.setIdTicket(TarefaDTO.idTicket());
        Tarefa.setNome(TarefaDTO.nome());
        Tarefa.setStatus(TarefaDTO.status());
        Tarefa.setExecutor(TarefaDTO.executor());
        return toDTO(Tarefa);
    }

    public TarefaDTO toDTO(Tarefa Tarefa) {
        return new TarefaDTO(
                Tarefa.getId(),
                Tarefa.getIdTicket(),
                Tarefa.getNome(),
                Tarefa.getStatus(),
                Tarefa.getExecutor()
        );
    }

    public Tarefa toEntity(TarefaDTO TarefaDTO) {
        return new Tarefa(
                TarefaDTO.id(),
                TarefaDTO.idTicket(),
                TarefaDTO.nome(),
                TarefaDTO.status(),
                TarefaDTO.executor()
        );
    }
}
