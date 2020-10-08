package com.best2bee.products.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.best2bee.products.model.Sale;
import com.best2bee.products.service.SaleService;

@RestController
public class SaleResource {
	
	@Autowired
	SaleService service;
	
	@PostMapping("/sale")
	public ResponseEntity<Sale> insert(@Valid @RequestBody Sale sale) {
		sale = service.create(sale);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/sale/{id}")
				.buildAndExpand(sale.getId()).toUri();
		
		return ResponseEntity.created(uri).body(sale);	
	}
	
	@GetMapping("/sale/{id}")
	public ResponseEntity<Sale> selectById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
		
	}
	
	@GetMapping("/sales/")
	public ResponseEntity<Page<Sale>> paginatedList(@RequestParam(name = "search", defaultValue = "") String searchTerm,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return ResponseEntity.ok().body(service.paginatedList(searchTerm, page, size));

	}
			
			
	
	
	
}
