package br.com.vectordev.clinicarweb.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vectordev.clinicarweb.dto.cliente.ClienteDto;
import br.com.vectordev.clinicarweb.entidade.cliente.ClienteEntity;
import br.com.vectordev.clinicarweb.repository.cliente.IClienteRepository;
import br.com.vectordev.clinicarweb.service.cliente.IClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List <ClienteEntity>> listarClientes() {		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Boolean> cadastrarCliente(@Valid @RequestBody ClienteDto cliente) {		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.cadastrarCliente(cliente));		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteEntity> consultarCliente(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.clienteService.consultar(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletarCliente(@PathVariable Long id) {		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.excluir(id));				
	}
	
	@PutMapping
	public ResponseEntity<Boolean> atualizaCliente(@Valid @RequestBody ClienteDto cliente) {		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.atualizar(cliente));		
	}
		
		
}
