package com.anderson.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anderson.cursomc.domain.ItemPedido;
import com.anderson.cursomc.domain.PagamentoComBoleto;
import com.anderson.cursomc.domain.Pedido;
import com.anderson.cursomc.domain.enums.StatusPagamento;
import com.anderson.cursomc.repositories.ItemPedidoRepository;
import com.anderson.cursomc.repositories.PagamentoRepository;
import com.anderson.cursomc.repositories.PedidoRepository;
import com.anderson.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
    private BoletoService boletoService;
	
	@Autowired
    private PagamentoRepository pagamentoRepository;
	
	@Autowired
    private ProdutoService produtoService;
	
	@Autowired
    private ItemPedidoRepository itemPedidoRepository;

	public Pedido find(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.getPagamento().setStatus(StatusPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        
        if(pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagamentoComBoleto, pedido.getInstante());
            
        }
        
        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        
        for(ItemPedido itemPedido : pedido.getItens()) {
            itemPedido.setDesconto(0.0);
            itemPedido.setPreco(produtoService.find(itemPedido.getProduto().getId()).getPreco());
            itemPedido.setPedido(pedido);
        }
        
        itemPedidoRepository.saveAll(pedido.getItens());
      
        return pedido;
    }

}
