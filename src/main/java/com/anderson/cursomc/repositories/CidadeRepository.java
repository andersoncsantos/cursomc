package com.anderson.cursomc.repositories;

import com.anderson.cursomc.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Query("SELECT cid FROM Cidade cid WHERE cid.estado.id = :estadoId ORDER BY cid.nome")
    List<Cidade> findCidades(@Param("estadoId") Integer estadoId);
}