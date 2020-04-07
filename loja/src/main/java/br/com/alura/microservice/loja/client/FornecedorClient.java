package br.com.alura.microservice.loja.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.alura.microservice.loja.dto.FornecedorDTO;
import br.com.alura.microservice.loja.dto.ItemDaCompraDTO;
import br.com.alura.microservice.loja.dto.PedidoDTO;

@FeignClient("fornecedor")
public interface FornecedorClient {
	
	@RequestMapping("/fornecedor/{estado}")
	FornecedorDTO getFornecedorPorEstado(@PathVariable String estado);

	@RequestMapping(method = RequestMethod.POST, value="/pedido")
	PedidoDTO realizaPedido(List<ItemDaCompraDTO> itens);
	
}
