package br.com.banco.mapper;

import br.com.banco.dto.ContaDTO;
import br.com.banco.entities.Conta;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContaMapper {

    public Conta toEntity(ContaDTO dto){
        return new Conta(dto.getId(),dto.getNome());
    }

    public ContaDTO toDTO(Conta entity){
        return new ContaDTO(entity.getIdConta(),entity.getNomeResponsavel());
    }

    public List<Conta> toEntity(List<ContaDTO> dtoList){
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<ContaDTO> toDTO(List<Conta> entityList){
        return entityList.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
