package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.highfourm.entity.Process;


public interface ProcessRepository extends JpaRepository<Process, String> {

	List<Process> findByProductProductId(String productId);

}
