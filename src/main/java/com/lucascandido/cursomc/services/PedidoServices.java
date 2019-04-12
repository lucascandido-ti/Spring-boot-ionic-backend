package com.lucascandido.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucascandido.cursomc.domain.ItemPedido;
import com.lucascandido.cursomc.domain.PagamentoComBoleto;
import com.lucascandido.cursomc.domain.Pedido;
import com.lucascandido.cursomc.domain.enums.EstadoPagamento;
import com.lucascandido.cursomc.repositories.ItemPedidoRepository;
import com.lucascandido.cursomc.repositories.PagamentoRepository;
import com.lucascandido.cursomc.repositories.PedidoRepository;
import com.lucascandido.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoServices {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoServices boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoServices produtoServices;
	
	@Autowired
	private ItemPedidoRepository ipRepository;
	
	@Autowired
	private ClienteServices clienteServices;
	
	public Pedido find(Integer id) {
		
			Optional<Pedido> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado !\nID: "+id+"Tipo: "+Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteServices.find(obj.getCliente().getId()));
		obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoServices.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		ipRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}
}