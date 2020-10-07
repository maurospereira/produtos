package com.best2bee.products.service;

import java.time.Instant;
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
import com.best2bee.products.model.Product;
import com.best2bee.products.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public Product findById(Long id) {

		Optional<Product> product = repository.findById(id);

		return product.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Product create(Product product) {
		try {
			product.setCreateDate(Instant.now());
			return repository.save(product);

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

			throw new DatabaseException("Esse produto não pôde ser excluido!");
		}

	}

	public Page<Product> paginatedList(String searchTerm, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");

		return repository.paginatedList(searchTerm.toLowerCase(), pageRequest);
	}

	@Transactional
	public Product updateById(Long id, Product newObj) {

		try {

			Product oldObj = repository.getOne(id);
			updateData(oldObj, newObj);

			return repository.save(oldObj);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());

		}

	}

	public void updateData(Product oldObj, Product newObj) {
		oldObj.setName(newObj.getName());
		oldObj.setPrice(newObj.getPrice());
		oldObj.setAmount(newObj.getAmount());

	}

	

}
