package br.com.vectordev.clinicarweb.entidade.atendimento;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.vectordev.clinicarweb.entidade.cliente.ClienteEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="atendimento")
@Data
@NoArgsConstructor
public class AtendimentoEntity implements Serializable {
	
	private static final long serialVersionUID = -1715718994649235154L;
	
	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="cod_atend")
	private Long id;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	private ClienteEntity cliente;
	
	@Column(name="data", columnDefinition="TIMESTAMP")
	private LocalDateTime data;
	
	@Column(name="acompanhante")
	private String acompanhante;
	
	@Column(name="ativo", nullable=false)
	private Boolean ativo;
	

}
