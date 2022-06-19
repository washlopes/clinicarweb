package br.com.vectordev.clinicarweb.exception.cliente;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ClienteException extends RuntimeException {

	private static final long serialVersionUID = -4144354578706213307L;
	
	private final HttpStatus httpStatus;
	
	public ClienteException(final String mensagem, final HttpStatus httpStatus) {
		super(mensagem);
		this.httpStatus = httpStatus;
	}

}
