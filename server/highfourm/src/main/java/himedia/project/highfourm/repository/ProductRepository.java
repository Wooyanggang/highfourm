package himedia.project.highfourm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.entity.pk.ProductPk;

public interface ProductRepository extends JpaRepository<Product, ProductPk>{
	
}
