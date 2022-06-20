package br.com.vectordev.clinicarweb.dto.cliente;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import br.com.vectordev.clinicarweb.entidade.cliente.Cor;
import br.com.vectordev.clinicarweb.entidade.cliente.EstadoCivil;
import br.com.vectordev.clinicarweb.entidade.cliente.Sexo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDto {

	
	private Long id;
	
	@NotBlank(message="Informe o nome do cliente")
	private String nome;
	
	@NotBlank(message="Informe o sexo do cliente")
	private Sexo sexo;
	
	@NotBlank(message="Informe a cor do cliente")
	private Cor cor;
	
	@NotBlank(message="Informe o estado civil do cliente")
	private EstadoCivil estadoCivil;
	
	@NotBlank(message="Informe a data de nascimento do cliente")
	private LocalDate dataNascimento;
	
	@NotBlank(message="Informe o nome da mãe do cliente")
	private String nomeMae;
	
	
	private String nomePai;
	
	@NotBlank(message="Informe o endereço do cliente")
	private String logradouro;
	
	@NotBlank(message="Informe o número do endereço do cliente")
	private String numero;
		
	
	private String complemento;
	
	@NotBlank(message="Informe o nome do bairro do cliente")	
	private String bairro;
	
	@NotBlank(message="Informe o nome do município do cliente")
	private String municipio;
	
	@NotBlank(message="Informe o cep do endereço do cliente")
	private Integer cep;
	
	
	private String profissao;
	
	
	private String telefoneResidencial;
	
	
	private String telefoneComercial;
	
	
	private String telefoneCelular;	
}
