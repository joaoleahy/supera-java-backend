package br.com.banco.mapper;

import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.entities.Transferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransferenciaMapper {

    @Autowired
    private ContaMapper contaMapper;

    public Transferencia toEntity(TransferenciaDTO dto){
        return new Transferencia(
                dto.getId(),
                dto.getDataTransferencia(),
                dto.getValor(),
                dto.getTipo(),
                dto.getNomeOperadorTransacao(),
                contaMapper.toEntity(dto.getConta()));
    }

    public TransferenciaDTO toDTO(Transferencia entity){
        return new TransferenciaDTO(
                entity.getId(),
                entity.getDataTransferencia(),
                entity.getValor(),
                entity.getTipo(),
                entity.getNomeOperadorTransacao(),
                contaMapper.toDTO(entity.getConta()));
    }

    public List<Transferencia> toEntity(List<TransferenciaDTO> dtoList){
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<TransferenciaDTO> toDTO(List<Transferencia> entityList){
        return entityList.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
