package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.project.highfourm.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
	@Query(value = "SELECT p FROM Product p where p.productName like ?1")
	Product findByProductName(String productName);
	
	 @Query("SELECT p.productName FROM Product p")
	    List<String> findAllProductNames();
}