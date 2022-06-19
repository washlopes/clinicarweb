package br.com.vectordev.clinicarweb.entidade.cliente;

public enum EstadoCivil {

	CASADO("Casado"),
	DIVORCIADO("Divorciado"),
	SOLTEIRO("Solteiro"),
	VIUVO("Viúvo");
	
	private String descricao;
	
	EstadoCivil(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
