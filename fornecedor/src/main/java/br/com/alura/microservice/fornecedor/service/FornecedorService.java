package br.com.alura.microservice.fornecedor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.microservice.fornecedor.model.Fornecedor;
import br.com.alura.microservice.fornecedor.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public Fornecedor getFornecedorPorEstado(String estado) {
		return fornecedorRepository.findByEstado(estado).orElse(new Fornecedor());
	}
}
