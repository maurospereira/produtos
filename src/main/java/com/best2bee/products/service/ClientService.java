package com.best2bee.products.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.best2bee.products.exceptions.DatabaseException;
import com.best2bee.products.exceptions.ResourceNotFoundException;
import com.best2bee.products.model.Client;
import com.best2bee.products.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public Client findById(Long id) {
		
		Optional<Client> client = repository.findById(id);
		
		return client.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Client create(Client client) {
		try {
			return repository.save(client);
			
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());

		}
	}
	
	public void deleteById(Long id) {
		try {
		repository.deleteById(id);
		
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);

		} catch (DataIntegrityViolationException e) {

			throw new DatabaseException("Este cliente não pôde ser excluido!");
		}

	}
	
	public Page<Client> paginatedList(String searchTerm, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");

		return repository.paginatedList(searchTerm.toLowerCase(), pageRequest);
	}
	
	@Transactional
	public Client updateById(Long id, Client newClient) {
		
		try {
			Client oldCliente = repository.getOne(id);
			updateData(oldCliente, newClient);
			
			return repository.save(oldCliente);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());

		}
	}
	
	public void updateData(Client oldCliente, Client newCliente) {
		oldCliente.setName(newCliente.getName());
		oldCliente.setCpf(newCliente.getCpf());
		oldCliente.setAddress(newCliente.getAddress());
	}

}
