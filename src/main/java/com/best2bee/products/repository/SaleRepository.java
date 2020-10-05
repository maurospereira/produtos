package com.best2bee.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.best2bee.products.model.Sale;

public interface SaleRepository  extends JpaRepository<Sale, Long>{

}
