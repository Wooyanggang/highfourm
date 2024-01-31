package himedia.project.highfourm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.highfourm.entity.StockManagement;

public interface StockManagementRepository extends JpaRepository<StockManagement, Integer> {
}