package br.com.airton;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.airton.domain.Categoria;
import br.com.airton.domain.Cidade;
import br.com.airton.domain.Cliente;
import br.com.airton.domain.Endereco;
import br.com.airton.domain.Estado;
import br.com.airton.domain.Pagamento;
import br.com.airton.domain.PagamentoComBoleto;
import br.com.airton.domain.PagamentoComCartao;
import br.com.airton.domain.Pedido;
import br.com.airton.domain.Produto;
import br.com.airton.domain.enums.EstadoPagamento;
import br.com.airton.domain.enums.TipoCliente;
import br.com.airton.repository.CategoriaRepository;
import br.com.airton.repository.CidadeRepository;
import br.com.airton.repository.ClienteRepository;
import br.com.airton.repository.EnderecoRepository;
import br.com.airton.repository.EstadoRepository;
import br.com.airton.repository.PagamentoRepository;
import br.com.airton.repository.PedidoRepository;
import br.com.airton.repository.ProdutoRepository;

@SpringBootApplication
public class Springrestjwth2Application implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Springrestjwth2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 80.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		
		Estado est1 = new Estado(null, "Minas Gerais"); 
		Estado est2 = new Estado(null, "São Paulo"); 
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Sao Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
	
		Cliente cli1 = new Cliente(null, "Maria", "maria@email.com", "0965142324", TipoCliente.PESSOAFISICA, null);
		cli1.getTelefones().addAll(Arrays.asList("3882-0922","3456-9089"));
		Endereco end1 = new Endereco(null, "RUA 1", "234", "s", "SP", "54420187", cli1, c1);
		Endereco end2 = new Endereco(null, "RUA 2", "2342", "s2", "SP2", "544201872", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
	
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(end1,end2));

		Pedido ped1 = new Pedido(null, LocalDateTime.of(2017, 9, 30, 10, 32) , cli1, end1);
		Pedido ped2 = new Pedido(null, LocalDateTime.of(2017, 10, 10, 19, 35) , cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, LocalDateTime.of(2017, 10, 20, 00, 00), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}

}

