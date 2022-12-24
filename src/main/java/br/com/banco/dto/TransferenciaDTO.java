package br.com.banco.dto;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransferenciaDTO {

    private Long id;
    private ZonedDateTime dataTransferencia;
    private Float valor;
    private String tipo;
    private String nomeOperadorTransacao;

    private ContaDTO conta;
}