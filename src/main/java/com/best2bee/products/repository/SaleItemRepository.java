package com.best2bee.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.best2bee.products.model.SaleItem;

public interface SaleItemRepository  extends JpaRepository<SaleItem, Long>{

}
