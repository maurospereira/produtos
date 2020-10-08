package com.best2bee.products.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.best2bee.products.model.Sale;

@Repository
public interface SaleRepository  extends JpaRepository<Sale, Long>{
	
	@Query ("FROM Sale s " + "WHERE LOWER(s.client.name) like %:searchTerm% ")
	Page<Sale> paginatedList(@Param("searchTerm") String searchTerm, Pageable pageable);

}
