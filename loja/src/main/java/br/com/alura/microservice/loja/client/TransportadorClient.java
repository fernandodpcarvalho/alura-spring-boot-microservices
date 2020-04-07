package br.com.alura.microservice.loja.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.alura.microservice.loja.dto.EntregaDTO;
import br.com.alura.microservice.loja.dto.VoucherDTO;

@FeignClient("transportador")
public interface TransportadorClient {

	@PostMapping("/entrega")
	VoucherDTO reservaEntrega(EntregaDTO entregaDTO);
	
}
