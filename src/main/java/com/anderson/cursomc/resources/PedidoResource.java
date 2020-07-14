package com.anderson.cursomc.resources;

import com.anderson.cursomc.domain.Pedido;
import com.anderson.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> findResource(@PathVariable Integer id) {
		Pedido pedido = pedidoService.findPedido(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertResource(@Valid @RequestBody Pedido pedido) {
	    pedido = pedidoService.insertPedido(pedido);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedido.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> findPageResource(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "lines", defaultValue = "24") Integer lines,
			@RequestParam(value = "orderby", defaultValue = "instante") String orderby,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<Pedido> pedido = pedidoService.findPagePedido(page, lines, orderby, direction);
		return ResponseEntity.ok().body(pedido);
	}

}
