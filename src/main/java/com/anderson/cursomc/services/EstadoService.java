package com.anderson.cursomc.services;

import com.anderson.cursomc.domain.Estado;
import com.anderson.cursomc.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAllEstado() {
        return estadoRepository.findAllByOrderByNome();
    }
}
