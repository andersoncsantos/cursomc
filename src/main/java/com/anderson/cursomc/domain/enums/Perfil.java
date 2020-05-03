package com.anderson.cursomc.domain.enums;

import lombok.Getter;

@Getter
public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");

	private Integer cod;
	private String descricao;

	Perfil(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

    public static Perfil toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (Perfil statusPagamento : Perfil.values()) {
			if (cod.equals(statusPagamento.getCod())) {
				return statusPagamento;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
