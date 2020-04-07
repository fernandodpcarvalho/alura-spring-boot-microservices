package br.com.alura.microservice.loja.service;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.client.TransportadorClient;
import br.com.alura.microservice.loja.dto.CompraDTO;
import br.com.alura.microservice.loja.dto.EntregaDTO;
import br.com.alura.microservice.loja.dto.FornecedorDTO;
import br.com.alura.microservice.loja.dto.PedidoDTO;
import br.com.alura.microservice.loja.dto.VoucherDTO;
import br.com.alura.microservice.loja.enums.CompraState;
import br.com.alura.microservice.loja.model.Compra;
import br.com.alura.microservice.loja.repository.CompraRepository;

@Service
public class CompraService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
	@Autowired
	private FornecedorClient fornecedorClient;

	@Autowired
	private TransportadorClient transportadorClient;
	
	@Autowired
	private CompraRepository compraRepository;

	@HystrixCommand(threadPoolKey = "getByIdThreadPool")
	public Compra getById(Long id) {
		return compraRepository.findById(id).orElse(new Compra());
	}

	@HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
	public Compra realizaCompra(CompraDTO compraDTO) {
		
		Compra novaCompra = new Compra();
		novaCompra.setState(CompraState.RECEBIDO);
		novaCompra.setEnderecoDestino(compraDTO.getEndereco().toString());
		compraRepository.save(novaCompra);
		
		compraDTO.setCompraId(novaCompra.getId());
		
		final String estado = compraDTO.getEndereco().getEstado();
		
		LOG.info("Buscando informações de fornecedor de {}", estado);
		FornecedorDTO fornecedor = fornecedorClient.getFornecedorPorEstado(estado);
		
		if(fornecedor.getEstado() == null) {
			throw new IllegalArgumentException("Não há fornecedor para este endereço "+estado);
		}

		LOG.info("Realizando um pedido");
		PedidoDTO pedido = fornecedorClient.realizaPedido(compraDTO.getItens());

		novaCompra.setState(CompraState.PEDIDO_REALIZADO);
		novaCompra.setFornecedor(fornecedor.getNome());
		novaCompra.setPedidoId(pedido.getId());
		novaCompra.setTempoDePreparo(pedido.getTempoDePreparo());
		compraRepository.save(novaCompra);
		
		//Simulação de problema no meio da execução
//		if(1 == 1) throw new RuntimeException();
		
		EntregaDTO entregaDTO = new EntregaDTO();
		entregaDTO.setPedidoId(pedido.getId());
		entregaDTO.setDataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()));
		entregaDTO.setEnderecoOrigem(fornecedor.getEndereco());
		entregaDTO.setEnderecoDestino(compraDTO.getEndereco().toString());
		VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDTO);

		novaCompra.setState(CompraState.ENTREGA_RESERVADA);
		novaCompra.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
		novaCompra.setVoucher(voucher.getNumero());
		compraRepository.save(novaCompra);
		
		return compraRepository.save(novaCompra);
	}
	
	public Compra realizaCompraFallback(CompraDTO compraDTO) {
		if(compraDTO.getCompraId() != null) {
			return compraRepository.findById(compraDTO.getCompraId()).orElse(new Compra());
		}
		Compra compraFallback = new Compra();
		compraFallback.setEnderecoDestino(compraDTO.getEndereco().toString());
		return compraFallback;
	}
	
	//Métodos que podem ser executados
	//Ex: Após três retentativas, cancela.
	//Pode ser oferecida a opção ao cliente.
	public Compra reprocessaCompra(Long id) {
		return null;
	}
	
	public Compra cancelaCompra(Long id) {
		return null;
	}
	
//	O método realizaCompra sabe exatamente todos os passos necessários para que uma compra seja efetivada. O que precisamos além disso é quem saiba o que fazer quando esta compra não chega no estado final. Cancelar, reprocessar ou tentar mais n vezes? Atualmente, nós temos o CompraController chamando o CompraService.
//	Poderíamos criar mais um serviço e colocar entre o controller e o serviço de compra. Teríamos então o CompraController chamando o OrquestradorDeCompraService, que chamaria o CompraService. De acordo com o estado da compra retornada pelo CompraService, o orquestrador decidiria reprocessar, cancelar, etc.
	
	
}
