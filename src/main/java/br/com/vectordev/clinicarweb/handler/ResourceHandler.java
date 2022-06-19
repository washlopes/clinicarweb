package br.com.vectordev.clinicarweb.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.vectordev.clinicarweb.exception.cliente.ClienteException;
import br.com.vectordev.clinicarweb.model.ErrorResponse;
import br.com.vectordev.clinicarweb.model.ErrorResponse.ErrorResponseBuilder;

@ControllerAdvice
public class ResourceHandler {

	@ExceptionHandler(ClienteException.class)
	public ResponseEntity <ErrorResponse> handlerClienteException(ClienteException c) {
		ErrorResponseBuilder erro = ErrorResponse.builder();
		erro.httpStatus(c.getHttpStatus().value());
		erro.mensagem(c.getMessage());
		erro.timeStamp(System.currentTimeMillis());
		return ResponseEntity.status(c.getHttpStatus()).body(erro.build());
	}
}
