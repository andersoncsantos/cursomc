package com.anderson.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

	public Pedido findPedido(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insertPedido(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.findCliente(pedido.getCliente().getId()));
        pedido.getPagamento().setStatus(StatusPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
        }
        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        for (ItemPedido itemPedido : pedido.getItens()) {
            itemPedido.setDesconto(0.0);
            itemPedido.setProduto(produtoService.findProduto(itemPedido.getProduto().getId()));
            itemPedido.setPreco(itemPedido.getProduto().getPreco());
            itemPedido.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItens());
        //emailService.sendOrderConfirmationEmail(pedido);
        //emailService.sendOrderConfirmationHtmlEmail(pedido);
        return pedido;
    }

}
