package com.anderson.cursomc.domain.enums;

import lombok.Getter;

@Getter
public enum StatusPagamento {

	PENDENTE(1, "Pendente"), 
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");

	private int cod;
	private String descricao;

	private StatusPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

    public static StatusPagamento toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (StatusPagamento statusPagamento : StatusPagamento.values()) {
			if (cod.equals(statusPagamento.getCod())) {
				return statusPagamento;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
