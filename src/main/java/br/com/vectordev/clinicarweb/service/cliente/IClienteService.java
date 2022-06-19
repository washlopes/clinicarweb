package br.com.vectordev.clinicarweb.service.cliente;

import java.util.List;

import br.com.vectordev.clinicarweb.entidade.cliente.ClienteEntity;

public interface IClienteService {
	
	public Boolean atualizar(final ClienteEntity cliente);
	
	public Boolean excluir(final Long id);
	
	public ClienteEntity consultar(final Long id);
	
	public List <ClienteEntity> findAll();
	
	public Boolean cadastrarCliente(final ClienteEntity cliente);	
	

}
