package br.com.alura.microservice.fornecedor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.microservice.fornecedor.model.Fornecedor;
import br.com.alura.microservice.fornecedor.service.FornecedorService;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
	
	private static final Logger LOG = LoggerFactory.getLogger(FornecedorController.class);
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@RequestMapping("/{estado}")
	public Fornecedor getFornecedorPorEstado(@PathVariable String estado) {
		LOG.info("Recebido requisição de informações do fornecedor de {}", estado );
		return fornecedorService.getFornecedorPorEstado(estado);
	}
}
