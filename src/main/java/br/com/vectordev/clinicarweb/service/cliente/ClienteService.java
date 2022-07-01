package br.com.vectordev.clinicarweb.service.cliente;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.vectordev.clinicarweb.controller.ClienteController;
import br.com.vectordev.clinicarweb.dto.cliente.ClienteDto;
import br.com.vectordev.clinicarweb.entidade.cliente.ClienteEntity;
import br.com.vectordev.clinicarweb.exception.cliente.ClienteException;
import br.com.vectordev.clinicarweb.repository.cliente.IClienteRepository;

@CacheConfig(cacheNames="cliente")
@Service
public class ClienteService implements IClienteService{

	private static final String MENSAGEM_ERRO = "Erro interno identificado. Contate o suporte";
	private static final String CLIENTE_NAO_ENCONTRADA = "Cliente não localizado";
	
	private IClienteRepository clienteRepository;
	
	private ModelMapper mapper;
	
	@Autowired
	public ClienteService(IClienteRepository clienteRepository) {
		this.mapper = new ModelMapper();
		this.clienteRepository = clienteRepository;
	}
	
	
	@CacheEvict(key="#cliente.id")
	@Override
	public Boolean atualizar(ClienteDto cliente) {
		try {			
						
			this.consultar(cliente.getId());
			ClienteEntity clienteAtualizado = this.mapper.map(cliente, ClienteEntity.class);			
			clienteRepository.save(clienteAtualizado);				
			
			return Boolean.TRUE;
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
			return Boolean.TRUE;			
		} catch (ClienteException c) {
			throw c;
		}
		catch (Exception e) {
			throw e;
		}
	}

	@CachePut(key="#id")
	@Override
	public ClienteDto consultar(Long id) {	
		try {
			Optional <ClienteEntity> clienteOptional = clienteRepository.findById(id);
			
			if (clienteOptional.isPresent()) {
				return this.mapper.map(clienteOptional.get(),ClienteDto.class);
			}			
			throw new ClienteException(CLIENTE_NAO_ENCONTRADA, HttpStatus.NOT_FOUND);
		} catch(ClienteException c) {
			throw c;
		} catch(Exception e) {
			throw new ClienteException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@CachePut(unless="#result.size()<3")
	@Override
	public List<ClienteDto> listar() {	
		try {
			
			List <ClienteDto> clientesDto = this.mapper.map(this.clienteRepository.findAll(),
					new TypeToken<List<ClienteDto>> () {}.getType());
			
			clientesDto.forEach(cliente -> {
				cliente.add(WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn(
								ClienteController.class).consultarCliente(cliente.getId())).withSelfRel());
			});
			return clientesDto;
			
		
		} catch (Exception e) {
			throw new ClienteException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	
	@Override
	public Boolean cadastrarCliente(ClienteDto cliente) {
		try {
			
			ClienteEntity clienteEntity = this.mapper.map(cliente, ClienteEntity.class);
			this.clienteRepository.save(clienteEntity);
			return Boolean.TRUE;
		} catch (Exception e) {
			throw new ClienteException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public List <ClienteDto> consultarClientePeloNome(String nome) {
		try {
			List <ClienteDto> clientes = this.mapper.map(this.clienteRepository
					.findByNome(nome), new TypeToken<List<ClienteDto>> () {}.getType());
			
			clientes.forEach(cliente -> {
				cliente.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class)
						.consultarClientePeloNome(cliente.getNome())).withSelfRel());
			});
			
			return clientes;
		} catch (Exception e) {
			throw new ClienteException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
