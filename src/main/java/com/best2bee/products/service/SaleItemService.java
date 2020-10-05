package com.best2bee.products.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.best2bee.products.exceptions.DatabaseException;

import com.best2bee.products.model.SaleItem;
import com.best2bee.products.repository.SaleItemRepository;

@Service
public class SaleItemService {
	@Autowired
	private SaleItemRepository repository;

	public SaleItem create(SaleItem item) {
		try {

			return repository.save(item);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());

		}

	}

}
