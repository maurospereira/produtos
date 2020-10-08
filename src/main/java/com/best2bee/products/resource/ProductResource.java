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

import com.best2bee.products.model.Product;
import com.best2bee.products.service.ProductService;

@RestController
@RequestMapping
public class ProductResource {
	
	@Autowired
	ProductService service;
	
	@PostMapping("/product")
	public ResponseEntity<Product> insert(@Valid @RequestBody Product product) {
		product = service.create(product);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produtos/{id}")
				.buildAndExpand(product.getId()).toUri();
		
		return ResponseEntity.created(uri).body(product);	
	}
	
	@DeleteMapping("/product/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);
	}
	
	@GetMapping("/products/")
	public ResponseEntity<Page<Product>> paginatedList(@RequestParam(name = "search", defaultValue = "") String searchTerm,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return ResponseEntity.ok().body(service.paginatedList(searchTerm, page, size));

	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> selectById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
		
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product newObj) {
		return ResponseEntity.ok().body(service.updateById(id, newObj));
	}
	

}
