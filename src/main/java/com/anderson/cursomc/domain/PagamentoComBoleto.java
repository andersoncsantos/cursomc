package com.anderson.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.anderson.cursomc.domain.enums.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;
	
	public PagamentoComBoleto(Integer id, StatusPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}
	
	
}
