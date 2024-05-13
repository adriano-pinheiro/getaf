package br.com.fiap.adj.techchallenge.getaf.service;

import br.com.fiap.adj.techchallenge.getaf.controller.exception.ControllerNotFoundException;
import br.com.fiap.adj.techchallenge.getaf.dto.ContratoDTO;
import br.com.fiap.adj.techchallenge.getaf.model.Contrato;
import br.com.fiap.adj.techchallenge.getaf.repository.ContratoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;

    @Autowired
    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    public Page<ContratoDTO> findAll(Pageable pageable){
        Page<Contrato> contratos = contratoRepository.findAll(pageable);
        return  contratos.map(this::toDTO);
    }

    public ContratoDTO findById(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() ->
                        new ControllerNotFoundException("Contrato não encontrado"));
        return toDTO(contrato);
    }

    public  ContratoDTO save(ContratoDTO contratoDTO) {
        final PasswordEncoder encoder = null;

        Contrato contrato = contratoRepository.save(toEntity(contratoDTO));


        contrato = contratoRepository.save(contrato);
        return toDTO(contrato);
    }

    public ContratoDTO update(Long id, ContratoDTO contratoDTO) {
        try {
            Contrato contrato =  contratoRepository.getOne(id);
            contrato.setCliente(contratoDTO.cliente());
            contrato.setHoras(contratoDTO.horas());
            contrato.setValor(contratoDTO.valor());
            contrato.setDtInicioVigencia(contratoDTO.dtInicioVigencia());
            contrato.setDtFimVigencia(contratoDTO.dtFimVigencia());
            contrato.setObservacao(contratoDTO.observacao());
            contrato = contratoRepository.save(contrato);
            return toDTO(contrato);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Contrato não encontrado");
        }
    }

    public void delete(Long id) {
        contratoRepository.deleteById(id);
    }

    private ContratoDTO toDTO(Contrato contrato) {
        return new ContratoDTO(
                contrato.getId(),
                contrato.getCliente(),
                contrato.getHoras(),
                contrato.getValor(),
                contrato.getDtInicioVigencia(),
                contrato.getDtFimVigencia(),
                contrato.getObservacao()
        );
    }

    private Contrato toEntity(ContratoDTO contratoDTO) {
        return new Contrato(
                contratoDTO.id(),
                contratoDTO.cliente(),
                contratoDTO.horas(),
                contratoDTO.valor(),
                contratoDTO.dtInicioVigencia(),
                contratoDTO.dtFimVigencia(),
                contratoDTO.observacao()
        );
    }
}
