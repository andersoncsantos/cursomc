package com.anderson.cursomc.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class CredenciaisDTO implements Serializable {

    private static final long serialVersionUID = -5796155058924845186L;

    private String email;
    private String senha;
}
