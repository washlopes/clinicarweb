package br.com.vectordev.clinicarweb.service.cliente;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.vectordev.clinicarweb.dto.cliente.ClienteDto;
import br.com.vectordev.clinicarweb.entidade.cliente.ClienteEntity;
import br.com.vectordev.clinicarweb.exception.cliente.ClienteException;
import br.com.vectordev.clinicarweb.repository.cliente.IClienteRepository;

@Service
public class ClienteService implements IClienteService{

	@Autowired
	private IClienteRepository clienteRepository;
	
	@Override
	public Boolean atualizar(ClienteDto cliente) {
		try {			
						
			ClienteEntity clienteAtual = consultar(cliente.getId());
			
			ModelMapper mapper = new ModelMapper();
			
			clienteAtual = mapper.map(cliente, ClienteEntity.class);
			
			clienteRepository.save(clienteAtual);				
			
			return true;
		} catch (ClienteException c) {
			throw c;
		}
		catch (Exception e) {
			throw e;
		}	
	}

	@Override
	public Boolean excluir(Long id) {
		try {
			this.consultar(id);
			this.clienteRepository.deleteById(id);
			return true;			
		} catch (ClienteException c) {
			throw c;
		}
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	public ClienteEntity consultar(Long id) {	
		try {
			Optional <ClienteEntity> clienteOptional = clienteRepository.findById(id);
			
			if (clienteOptional.isPresent()) {
				return clienteOptional.get();
			}			
			throw new ClienteException("Cliente não localizado!", HttpStatus.NOT_FOUND);
		} catch(ClienteException c) {
			throw c;
		} catch(Exception e) {
			throw new ClienteException("Erro não identificado. Contate o suporte!", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	public List<ClienteEntity> findAll() {		
		return this.clienteRepository.findAll();
	}

	@Override
	public Boolean cadastrarCliente(ClienteDto cliente) {
		try {
			ModelMapper mapper = new ModelMapper();
			ClienteEntity clienteEntity = mapper.map(cliente, ClienteEntity.class);
			this.clienteRepository.save(clienteEntity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}	

}
