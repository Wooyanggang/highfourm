package himedia.project.highfourm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.highfourm.entity.StockManagement;

public interface StockManagementRepository extends JpaRepository<StockManagement, Long> {
	Optional<StockManagement> findById(Long managementId);
}
