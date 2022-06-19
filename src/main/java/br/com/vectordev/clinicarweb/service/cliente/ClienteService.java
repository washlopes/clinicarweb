package br.com.vectordev.clinicarweb.service.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vectordev.clinicarweb.model.cliente.ClienteEntity;
import br.com.vectordev.clinicarweb.repository.cliente.IClienteRepository;

@Service
public class ClienteService implements IClienteService{

	@Autowired
	private IClienteRepository clienteRepository;
	
	@Override
	public Boolean atualizar(ClienteEntity cliente) {
		try {
			ClienteEntity clienteAtual = clienteRepository.findById(cliente.getId()).get();
			clienteAtual = cliente;		
			clienteRepository.save(clienteAtual);
			return true;
		} catch (Exception e) {
			return false;
		}	
	}

	@Override
	public Boolean excluir(Long id) {
		try {
			this.clienteRepository.deleteById(id);
			return true;			
		} catch (Exception e) {
			return false;
		}
	}

}
