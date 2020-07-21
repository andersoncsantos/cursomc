package com.anderson.cursomc.domain.dto;

import com.anderson.cursomc.domain.Cidade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class CidadeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public CidadeDTO(Cidade cidade) {
        id = cidade.getId();
        nome = cidade.getNome();
    }
}
