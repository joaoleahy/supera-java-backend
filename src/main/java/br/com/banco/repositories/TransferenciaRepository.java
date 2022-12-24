package br.com.banco.repositories;

import br.com.banco.entities.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia,Long> {

    @Query("SELECT t FROM Transferencia t WHERE t.conta.idConta = ?1")
    public List<Transferencia> findByContaId(Long id);

    @Query("SELECT t FROM Transferencia t WHERE t.conta.idConta = :contaId AND t.dataTransferencia BETWEEN :dataInicio AND :dataFim")
    public List<Transferencia> findByDate(Long contaId,ZonedDateTime dataInicio, ZonedDateTime dataFim);

    @Query("SELECT t FROM Transferencia t WHERE t.conta.idConta = :contaId AND t.nomeOperadorTransacao LIKE %:operador% AND t.dataTransferencia BETWEEN :dataInicio AND :dataFim")
    public List<Transferencia> search(Long contaId, String operador, ZonedDateTime dataInicio, ZonedDateTime dataFim);
}