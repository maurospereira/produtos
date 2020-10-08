package com.best2bee.products.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.best2bee.products.model.Client;
import com.best2bee.products.service.ClientService;

@RestController
@RequestMapping
public class ClientResource {
	
	@Autowired
	ClientService service;
	
	
	@PostMapping("/client")
	public ResponseEntity<Client> insert(@Valid @RequestBody Client client) {
		client = service.create(client);	
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/client/{id}")
				.buildAndExpand(client.getId()).toUri();
		
		return ResponseEntity.created(uri).body(client);
	}
	
	@DeleteMapping("/client/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);
	}
	
	@GetMapping("/clients")
	public ResponseEntity<Page<Client>> paginatedList(@RequestParam(name = "search", defaultValue = "") String searchTerm,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return ResponseEntity.ok().body(service.paginatedList(searchTerm, page, size));

	}
	
	@GetMapping("/client/{id}")
	public ResponseEntity<Client> selectById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
		
	}
	
	@PutMapping("/client/{id}")
	public ResponseEntity<Client> update(@PathVariable Long id, @Valid @RequestBody Client newClient) {
		return ResponseEntity.ok().body(service.updateById(id, newClient));
	}
	
}
