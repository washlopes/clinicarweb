package br.com.vectordev.clinicarweb.service.cliente;

import br.com.vectordev.clinicarweb.model.cliente.ClienteEntity;

public interface IClienteService {
	
	public Boolean atualizar(final ClienteEntity cliente);
	
	public Boolean excluir(final Long id);

}
