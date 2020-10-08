package com.best2bee.products.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.best2bee.products.model.Address;
import com.best2bee.products.model.Client;
import com.best2bee.products.model.Product;
import com.best2bee.products.model.Sale;
import com.best2bee.products.model.SaleItem;
import com.best2bee.products.repository.ClientRepository;
import com.best2bee.products.repository.ProductRepository;
import com.best2bee.products.repository.SaleItemRepository;
import com.best2bee.products.repository.SaleRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired 
	private SaleRepository saleRepository;
	
	@Autowired 
	private SaleItemRepository saleItemRepository;

	@Override
	public void run(String... args) throws Exception {

		Address a1 = new Address(null, "Estrada união industria", "Centro", 12, "25730-281", "Petropolis", "RJ", "");
		Address a2 = new Address(null, "Estrada da saldade", "Centro", 12, "23754-231", "Petropolis", "RJ", "");

		Client c1 = new Client(null, "Paulo dos Santos", "16887338092", a1);

		Client c2 = new Client(null, "Maria Luiza Fontes", "10394678060", a2);

		Product p1 = new Product(null,
				"Smartphone Samsung Galaxy S10e 128GB Dual Chip Android 9.0 Tela 5,8\" Octa-Core 4G Câmera 12MP + 16MP - Preto",
				Instant.now(), 2.06910, 10F);

		Product p2 = new Product(null, "iPhone 11 Pro Max 512GB Cinza Espacial iOS 4G + Wi-Fi Câmera 12MP",
				Instant.now(), 8.499, 10F);		
		

		Sale s1 = new Sale(null,Instant.now(), c1);
		
		SaleItem si1 = new SaleItem(null,p1, s1, 20F, 2.000 );
		SaleItem si2 = new SaleItem(null,p2, s1, 20F, 8.490 );
		
		productRepository.saveAll(Arrays.asList(p1, p2));

		clientRepository.saveAll(Arrays.asList(c1, c2));
		
		saleRepository.save(s1);
		
		saleItemRepository.saveAll(Arrays.asList(si1,si2));		

	}

}
