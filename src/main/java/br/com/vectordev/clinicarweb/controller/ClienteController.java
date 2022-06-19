package br.com.vectordev.clinicarweb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@GetMapping("/")
	public ResponseEntity<String> helloWorldRest() {
		return ResponseEntity.status(HttpStatus.OK).body("Olá Mundo Rest!");
	}
	
}
