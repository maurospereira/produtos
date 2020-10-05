package com.best2bee.products.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.best2bee.products.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("FROM Product p " + "WHERE LOWER(p.name) like %:searchTerm% ")
	Page<Product> paginatedList(@Param("searchTerm") String searchTerm, Pageable pageable);

}
