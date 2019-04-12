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
import com.lucascandido.cursomc.services.BoletoServices;

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
	
	public Pedido find(Integer id) {
		
			Optional<Pedido> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado !\nID: "+id+"Tipo: "+Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
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
			ip.setPreco(produtoServices.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		ipRepository.saveAll(obj.getItens());
		return obj;
	}
}