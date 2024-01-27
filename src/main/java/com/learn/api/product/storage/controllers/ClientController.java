package com.learn.api.product.storage.controllers;

import java.util.List;
import java.util.Optional;

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

import com.learn.api.product.storage.entities.Client;
import com.learn.api.product.storage.exceptions.ClientNotFoundException;
import com.learn.api.product.storage.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll(){
		return new ResponseEntity<List<Client>>(clientService.getAllClients(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> findById(@PathVariable(value="id") Long id){
		Optional<Client> client = clientService.getElementById(id);
		if(!client.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
			throw new ClientNotFoundException("Client not found");
		}
		return new ResponseEntity<Client>(client.get(), HttpStatus.OK);
	}
	
	@PostMapping(path = "/register")
	public ResponseEntity<Client> save(@Valid @RequestBody Client client){
		Client cli = clientService.saveClient(client);
		if(cli == null) {
			throw new ClientNotFoundException("Client not create!");
		}
		else {
			return new ResponseEntity<>(cli, HttpStatus.CREATED);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client updateClient){
		return clientService.getElementById(id)
	            .map(client -> {
	                client.setName(updateClient.getName());
	                client.setEmail(updateClient.getEmail());
	                client.setCpf(updateClient.getCpf());
	                client.setAddress(updateClient.getAddress());
	                Client updatedClient = clientService.saveClient(client);
	                return ResponseEntity.ok(updatedClient);
	            })
	            .orElseThrow(() -> new ClientNotFoundException("Client not exist with id " + id));
	}
	
	@DeleteMapping(path = "/disable/{id}")
	public void deleteClient(@PathVariable Long id){
		clientService.deleteClient(id);
	}
	
	
}
