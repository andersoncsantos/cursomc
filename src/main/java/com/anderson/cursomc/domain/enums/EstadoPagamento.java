package com.anderson.cursomc.domain.enums;

import lombok.Getter;

@Getter
public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), 
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");

	private Integer cod;
	private String descricao;

	private EstadoPagamento(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (EstadoPagamento tipoEstadoPagamento : EstadoPagamento.values()) {
			if (cod.equals(tipoEstadoPagamento.getCod())) {
				return tipoEstadoPagamento;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
