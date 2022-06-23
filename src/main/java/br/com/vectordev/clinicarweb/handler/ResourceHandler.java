package br.com.vectordev.clinicarweb.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.vectordev.clinicarweb.exception.cliente.ClienteException;
import br.com.vectordev.clinicarweb.model.ErrorMapResponse;
import br.com.vectordev.clinicarweb.model.ErrorMapResponse.ErrorMapResponseBuilder;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity <ErrorMapResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException c) {
		
		Map <String, String> erros = new HashMap <> ();
		
		c.getBindingResult().getAllErrors().forEach(erro -> {
			String campo = ((FieldError) erro).getField();
			String mensagem = erro.getDefaultMessage();
			erros.put(campo, mensagem);
		});
		
		ErrorMapResponseBuilder erroMap = ErrorMapResponse.builder();
		erroMap.erros(erros).httpStatus(HttpStatus.BAD_REQUEST.value())
		.timeStamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroMap.build());
	}
}
