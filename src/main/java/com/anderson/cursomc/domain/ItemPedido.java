package com.anderson.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class ItemPedido implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
	    super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
    public double getSubTotal() {
        return (preco - desconto) * quantidade;
    }
	
	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public void setPedido(Pedido pedido) {
	    id.setPedido(pedido);
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produto produto) {
        id.setProduto(produto);
    }

	@Override
	public String toString() {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		final StringBuffer sb = new StringBuffer();
		sb.append(getProduto().getNome());
		sb.append(", Quantidade: ");
		sb.append(getQuantidade());
		sb.append(", Preço Unitário: ");
		sb.append(numberFormat.format(getPreco()));
		sb.append(", Subtotal: ");
		sb.append(numberFormat.format(getSubTotal()));
		sb.append("\n");
		return sb.toString();
	}
}
