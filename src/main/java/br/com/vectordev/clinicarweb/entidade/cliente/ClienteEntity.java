package br.com.vectordev.clinicarweb.entidade.cliente;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.vectordev.clinicarweb.entidade.atendimento.AtendimentoEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cliente")
@Data
@NoArgsConstructor
public class ClienteEntity implements Serializable {
	
	private static final long serialVersionUID = -7333805673090637913L;

	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(generator="cod_cli")
	@GenericGenerator(name="cod_cli", strategy="increment")
	@Column(name="cod_cli")
	private Long id;
	
	@JsonInclude(Include.NON_EMPTY)
	@Column(name="nome")
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
	@Enumerated(EnumType.STRING)
	@Column(name="sexo")
	private Sexo sexo;
	
	@JsonInclude(Include.NON_NULL)
	@Enumerated(EnumType.STRING)
	@Column(name="cor")
	private Cor cor;
	
	@JsonInclude(Include.NON_NULL)
	@Enumerated(EnumType.STRING)
	@Column(name="estado_civil")
	private EstadoCivil estadoCivil;
	
	@JsonInclude(Include.NON_NULL)	
	@Column(name="data_nascimento")
	private LocalDate dataNascimento;
	
	@JsonInclude(Include.NON_EMPTY)
	@Column(name="nm_mae")
	private String nomeMae;
	
	@Column(name="nm_pai")
	private String nomePai;
	
	@JsonInclude(Include.NON_EMPTY)
	@Column(name="logradouuro")
	private String logradouro;
	
	@JsonInclude(Include.NON_EMPTY)
	@Column(name="numero")
	private String numero;
		
	@Column(name="complemento")
	private String complemento;
	
	@JsonInclude(Include.NON_EMPTY)
	@Column(name="bairro")
	private String bairro;
	
	@JsonInclude(Include.NON_EMPTY)
	@Column(name="municipio")
	private String municipio;
	
	@JsonInclude(Include.NON_EMPTY)
	@Column(name="cep")
	private Integer cep;
	
	@Column(name="profissao")
	private String profissao;
	
	@Column(name="tel_residencial")
	private String telefoneResidencial;
	
	@Column(name="tel_comercial")
	private String telefoneComercial;
	
	@Column(name="tel_celular")
	private String telefoneCelular;	
	
	@OneToMany(mappedBy="cliente",
			cascade = CascadeType.ALL,
			orphanRemoval=true)	
	private List <AtendimentoEntity> atendimentos;
	
	public void addAtendimento(AtendimentoEntity atendimento) {
		atendimentos.add(atendimento);
		atendimento.setCliente(this);
	}
	
	public void removeAtendimento(AtendimentoEntity atendimento) {
		atendimentos.remove(atendimento);
		atendimento.setCliente(null);
	}

}
