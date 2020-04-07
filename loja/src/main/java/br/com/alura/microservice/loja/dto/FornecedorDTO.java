package br.com.alura.microservice.loja.dto;

public class FornecedorDTO {
	
	private String nome;
	
	private String estado;
	
	private String endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return endereco + " - " +estado;
	}
	
	
}
