package com.anderson.cursomc.domain.dto;

import com.anderson.cursomc.domain.Estado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class EstadoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public EstadoDTO(Estado estado) {
        id = estado.getId();
        nome = estado.getNome();
    }

}
