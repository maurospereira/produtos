package com.best2bee.products.service;

import java.time.Instant;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.best2bee.products.exceptions.DatabaseException;
import com.best2bee.products.exceptions.ResourceNotFoundException;
import com.best2bee.products.model.Sale;
import com.best2bee.products.model.SaleItem;
import com.best2bee.products.repository.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Autowired
	private SaleItemService saleItemService;

	public Sale findById(Long id) {

		Optional<Sale> sale = repository.findById(id);

		return sale.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Sale create(Sale sale) {
		try {

			sale.setCreateDate(Instant.now());

			Sale newSale = repository.save(sale);

			for (SaleItem item : sale.getItems()) {
				item.setSale(newSale);
				saleItemService.create(item);
			}
			
			return newSale;

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}
	
	public Page<Sale> paginatedList (String searchTerm, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "client.name");
		
		return repository.paginatedList(searchTerm.toLowerCase(), pageRequest);
	}

}
