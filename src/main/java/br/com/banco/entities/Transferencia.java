package br.com.banco.entities;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private ZonedDateTime dataTransferencia;

    @Column(nullable = false,precision = 20,scale = 2)
    private Float valor;

    @Column(nullable = false,length = 15)
    private String tipo;

    @Column(length = 50)
    private String nomeOperadorTransacao;

    @ManyToOne
    @JoinColumn(name = "conta_id",nullable = false)
    private Conta conta;
}
