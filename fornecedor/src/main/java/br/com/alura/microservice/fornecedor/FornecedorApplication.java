package br.com.alura.microservice.fornecedor;

//import java.math.BigDecimal;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import br.com.alura.microservice.fornecedor.enums.Estado;
//import br.com.alura.microservice.fornecedor.model.Fornecedor;
//import br.com.alura.microservice.fornecedor.model.Produto;
//import br.com.alura.microservice.fornecedor.repository.FornecedorRepository;
//import br.com.alura.microservice.fornecedor.repository.ProdutoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication()
@EnableResourceServer
public class FornecedorApplication {
	
//	@Autowired
//	private ProdutoRepository produtoRepository;
//	
//	@Autowired
//	private FornecedorRepository fornecedorRepository;

	public static void main(String[] args) {
		SpringApplication.run(FornecedorApplication.class, args);
	}
	
//	@Bean
//	public CommandLineRunner comandLineRunner() {
//		return args -> {
//			this.produtoRepository.save(new Produto("BlueBier", Estado.SP.toString(), "Cervela de trigo BlueBier", new BigDecimal(12.90)));
//			this.produtoRepository.save(new Produto("GreenBier", Estado.SP.toString(), "Cervela Pilsen GreenBier", new BigDecimal(07.50)));
//			this.produtoRepository.save(new Produto("YellowBier", Estado.SP.toString(), "Cervela Ipa YellowBier", new BigDecimal(19.75)));
//			this.produtoRepository.save(new Produto("YellowBier", Estado.RJ.toString(), "Cervela Ipa YellowBier", new BigDecimal(19.75)));
//			this.produtoRepository.save(new Produto("BlackBier", Estado.RJ.toString(), "Cervela escura BlackBier", new BigDecimal(06.90)));
//			this.produtoRepository.save(new Produto("WhiteBier", Estado.RJ.toString(), "Cervela de trigo WhiteBier", new BigDecimal(21.90)));
//
//			this.fornecedorRepository.save(new Fornecedor("Fornecedor SP", Estado.SP.toString(), "Rua dos Pingucos, 51"));
//			this.fornecedorRepository.save(new Fornecedor("Fornecedor RJ", Estado.RJ.toString(), "Rua Mesa de Bar, 999"));
//		};
//	}

}
