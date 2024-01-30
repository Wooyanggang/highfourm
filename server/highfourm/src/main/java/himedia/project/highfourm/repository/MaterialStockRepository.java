package himedia.project.highfourm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.highfourm.entity.Material;
import himedia.project.highfourm.entity.MaterialStock;

public interface MaterialStockRepository extends JpaRepository<MaterialStock, Long> {

}
