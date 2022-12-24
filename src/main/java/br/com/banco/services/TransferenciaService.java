package br.com.banco.services;

import br.com.banco.exceptions.IdNotFoundException;
import br.com.banco.exceptions.NullFieldsException;
import br.com.banco.mapper.TransferenciaMapper;
import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.utils.ExceptionThrowller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaMapper mapper;

    @Autowired
    private TransferenciaRepository repository;

    @Autowired
    private ExceptionThrowller throwller;

    public TransferenciaDTO create(TransferenciaDTO dto) throws NullFieldsException {
        throwller.IfNullThrow(dto);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public TransferenciaDTO delete(Long id) throws IdNotFoundException{
        Transferencia transferenciaOptional = (Transferencia) throwller.FindOrThrow(id,repository);
        repository.delete(transferenciaOptional);
        return mapper.toDTO(transferenciaOptional);
    }

    public TransferenciaDTO update(Long id,TransferenciaDTO dto) throws IdNotFoundException,NullFieldsException {
        Transferencia transferenciaOptional = (Transferencia) throwller.FindOrThrow(id,repository);
        throwller.IfNullThrow(dto);
        dto.setId(transferenciaOptional.getId());
        transferenciaOptional = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(transferenciaOptional));
    }

    public List<TransferenciaDTO> read(Long id){
        return mapper.toDTO(repository.findByContaId(id));
    }

    public List<TransferenciaDTO> read(Long id,LocalDate dataInicio, LocalDate dataFim){
        return mapper.toDTO(
                repository.findByDate(
                        id,
                        dataInicio.atStartOfDay(ZoneId.systemDefault()),
                        dataFim.atStartOfDay(ZoneId.systemDefault())));
    }

    public List<TransferenciaDTO> read(Long id, String operador, LocalDate dataInicio, LocalDate dataFim){

        return mapper.toDTO(
                repository.search(
                        id,
                        operador,
                        dataInicio.atStartOfDay(ZoneId.systemDefault()),
                        dataFim.atStartOfDay(ZoneId.systemDefault())));
    }
}
