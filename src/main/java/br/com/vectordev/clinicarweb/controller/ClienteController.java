package br.com.vectordev.clinicarweb.controller;

import java.util.List;

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

import br.com.vectordev.clinicarweb.model.cliente.ClienteEntity;
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
		
		return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Boolean> cadastrarCliente(@RequestBody ClienteEntity cliente) {
		try {
			this.clienteRepository.save(cliente);
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(false);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteEntity> consultarCliente(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.clienteRepository.findById(id).get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletarCliente(@PathVariable Long id) {
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(clienteService.excluir(id));
				
	}
	
	@PutMapping
	public ResponseEntity<Boolean> atualizaCliente(@RequestBody ClienteEntity cliente) {
		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.atualizar(cliente));
			
		
	}
		
		
}
