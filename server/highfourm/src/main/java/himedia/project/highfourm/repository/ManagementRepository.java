package himedia.project.highfourm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.highfourm.entity.StockManagement;

public interface ManagementRepository extends JpaRepository<StockManagement, Integer>{

}
