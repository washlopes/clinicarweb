package br.com.vectordev.clinicarweb.repository.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vectordev.clinicarweb.entidade.cliente.ClienteEntity;
import br.com.vectordev.clinicarweb.entidade.cliente.Cor;
import br.com.vectordev.clinicarweb.entidade.cliente.EstadoCivil;
import br.com.vectordev.clinicarweb.entidade.cliente.Sexo;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {

	/* @Query("select c from ClienteEntity c where c.cpf = :cpf")
	public List <ClienteEntity> findByCpf(@Param("cpf") int cpf); */
	
	@Query("select c from ClienteEntity c where c.nome like concat('%',:nome,'%')")
	public List <ClienteEntity> findByNome(@Param("nome") String nome);
	
	/* @Query("select c from ClienteEntity c where c.sexo = :sexo")
	public List <ClienteEntity> findBySexo(@Param("sexo") Sexo sexo);
	
	@Query("select c from ClienteEntity c where c.cor = :cor")
	public List <ClienteEntity> findByCor(@Param("cor") Cor cor);
	
	@Query("select c from ClienteEntity c where c.estadoCivil = :estadoCivil")
	public List <ClienteEntity> findByEstadoCivil(@Param("estadoCivil") EstadoCivil estadoCivil);
	
	@Query("select c from ClienteEntity c where c.municipio like :municipio")
	public List <ClienteEntity> findByMunicipio(@Param("municipio") String municipio);
	
	@Query("select c from ClienteEntity c where c.cep = :cep")
	public List <ClienteEntity> findByCep(@Param("cep") int cep); */
	
}


