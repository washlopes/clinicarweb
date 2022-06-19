package br.com.vectordev.clinicarweb.model.cliente;

public enum Cor {
	
	LEUCODERMA("Leucoderma"),
	MELANODERMA("Melanoderma");
	
	private String descricao;
	
	Cor(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
