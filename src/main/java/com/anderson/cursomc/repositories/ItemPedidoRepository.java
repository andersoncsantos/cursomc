package com.anderson.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anderson.cursomc.domain.Categoria;

@Repository
public interface ItemPedidoRepository extends JpaRepository<Categoria, Integer>{
	
	

}