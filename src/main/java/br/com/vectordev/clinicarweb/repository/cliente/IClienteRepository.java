package br.com.vectordev.clinicarweb.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vectordev.clinicarweb.entidade.cliente.ClienteEntity;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
