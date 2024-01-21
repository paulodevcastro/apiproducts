package com.learn.api.product.storage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learn.api.product.storage.entities.Client;
import com.learn.api.product.storage.repositories.ClientRepository;

@Service
public class ClientService {

	private ClientRepository clientRepository;
	
	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	public List<Client> getAllClients(){
		return clientRepository.findAll();
	}
	
	public Optional<Client> getElementById(Long id){
		return clientRepository.findById(id);
	}
	
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}
	
	public void deleteClient(Long id) {
		this.clientRepository.deleteById(id);
	}
}
