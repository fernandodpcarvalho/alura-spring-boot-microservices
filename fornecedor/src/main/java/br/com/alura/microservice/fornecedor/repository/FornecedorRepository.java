package br.com.alura.microservice.fornecedor.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.microservice.fornecedor.model.Fornecedor;

@Repository
public interface FornecedorRepository extends CrudRepository<Fornecedor, Long>{

	Optional<Fornecedor> findByEstado(String estado);
}
