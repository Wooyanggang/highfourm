package himedia.project.highfourm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.highfourm.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, String> {
	
}
