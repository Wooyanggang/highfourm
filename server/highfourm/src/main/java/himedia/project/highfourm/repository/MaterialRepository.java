package himedia.project.highfourm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import himedia.project.highfourm.entity.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, String> {
	
}
