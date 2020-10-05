package com.best2bee.products.repository;

import org.springframework.stereotype.Repository;

import com.best2bee.products.model.Client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query("FROM Client c " + "WHERE LOWER(c.name) like %:searchTerm% ")
	Page<Client> paginatedList(@Param("searchTerm") String searchTerm, Pageable pageable);

}
