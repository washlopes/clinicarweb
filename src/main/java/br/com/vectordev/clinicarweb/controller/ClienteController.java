package br.com.vectordev.clinicarweb.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
import br.com.vectordev.clinicarweb.model.Response;
import br.com.vectordev.clinicarweb.repository.cliente.IClienteRepository;
import br.com.vectordev.clinicarweb.service.cliente.IClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	private static final String DELETE = "DELETE";
	private static final String UPDATE = "UPDATE";
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private IClienteService clienteService;	
	
	
	
	@GetMapping
	public ResponseEntity<Response <List <ClienteDto>>> listarClientes() {	
		Response <List <ClienteDto>> response = new Response <> ();
		response.setData(this.clienteService.listar());
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(ClienteController.class)
				.listarClientes()).withSelfRel());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping
	public ResponseEntity<Boolean> cadastrarCliente(@Valid @RequestBody ClienteDto cliente) {		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.cadastrarCliente(cliente));		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<ClienteDto>> consultarCliente(@PathVariable Long id) {
		Response <ClienteDto> response = new Response <> ();
		response.setData(this.clienteService.consultar(id));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				ClienteController.class
				).consultarCliente(id)).withSelfRel());
		response.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(ClienteController.class)
				.deletarCliente(id)
				).withRel(DELETE));
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class).atualizaCliente(null)).withRel(UPDATE));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletarCliente(@PathVariable Long id) {		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.excluir(id));				
	}
	
	@PutMapping
	public ResponseEntity<Boolean> atualizaCliente(@Valid @RequestBody ClienteDto cliente) {		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.atualizar(cliente));		
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity <List<ClienteDto>> consultarClientePeloNome(@PathVariable String nome) {
		Response <List <ClienteDto>> response = new Response <>();
		List <ClienteDto> clientes = this.clienteService.consultarClientePeloNome(nome);
		response.setData(clientes);
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class)
				.consultarClientePeloNome(nome)).withSelfRel());
		return ResponseEntity.status(HttpStatus.OK).body(response.getData());
	}
		
}
