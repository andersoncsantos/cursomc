package com.anderson.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instante;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco enderecoDeEntrega;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public double getValorTotal() {
        double soma = 0.0;
        for (ItemPedido itemPedido : itens) {
            soma = soma + itemPedido.getSubTotal();
        }
        return soma;
    }

    public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        final StringBuffer sb = new StringBuffer();
        sb.append("Pedido Número: ");
        sb.append(getId());
        sb.append(" Data do Pedido: ");
        sb.append(simpleDateFormat.format(getInstante()));
        sb.append(", Cliente: ");
        sb.append(getCliente().getNome());
        sb.append(", Situação do Pagamento: ");
        sb.append(getPagamento().getStatus().getDescricao());
        sb.append("\nDetalhes:\n");
        for (ItemPedido itemPedido : getItens()) {
            sb.append(itemPedido.toString());
        }
        sb.append("Valor total: ");
        sb.append(numberFormat.format(getValorTotal()));
        return sb.toString();
    }
}
