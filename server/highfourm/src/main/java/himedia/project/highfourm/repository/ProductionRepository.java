package himedia.project.highfourm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.highfourm.entity.Product;

public interface ProductionRepository extends JpaRepository<Product, String> {

}
