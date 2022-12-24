package br.com.banco.service;

import br.com.banco.exceptions.IdNotFoundException;
import br.com.banco.exceptions.NullFieldsException;
import br.com.banco.mapper.ContaMapper;
import br.com.banco.dto.ContaDTO;
import br.com.banco.entities.Conta;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.utils.ExceptionThrowller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaMapper mapper;

    @Autowired
    private ContaRepository repository;

    @Autowired
    private ExceptionThrowller throwller;

    public ContaDTO create(ContaDTO dto) throws NullFieldsException {
        throwller.IfNullThrow(dto);
        return mapper.toDTO(
                repository.save(
                        mapper.toEntity(dto)));
    }

    public ContaDTO delete(Long id) throws IdNotFoundException {
        Conta contaOptional = (Conta) throwller.FindOrThrow(id,repository);
        repository.delete(contaOptional);
        return mapper.toDTO(contaOptional);
    }

    public ContaDTO update(Long id,ContaDTO dto) throws IdNotFoundException, NullFieldsException{
        Conta contaOptional = (Conta) throwller.FindOrThrow(id,repository);
        throwller.IfNullThrow(dto);
        contaOptional.setNomeResponsavel(dto.getNome());
        return mapper.toDTO(repository.save(contaOptional));
    }

    public List<ContaDTO> read(){
        return mapper.toDTO(repository.findAll());
    }

    public ContaDTO read(Long id) throws IdNotFoundException{
        return mapper.toDTO(
                (Conta) throwller.FindOrThrow(id,repository));
    }

}
