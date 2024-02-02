package himedia.project.highfourm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import himedia.project.highfourm.entity.StockManagement;

public interface StockManagementRepository extends JpaRepository<StockManagement, Integer> {

}
