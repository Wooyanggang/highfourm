package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import himedia.project.highfourm.dto.material.MaterialListResponseDTO;
import himedia.project.highfourm.entity.MaterialStock;

public interface MaterialStockRepository extends JpaRepository<MaterialStock, String> {
	@Query("SELECT new himedia.project.highfourm.dto.material.MaterialListResponseDTO("
	        + "ma.materialId, ma.materialName, ma.unit, sm.managementName, ms.totalStock, ms.safetyStock, ms.maxStock, ms.leadTime) "
	        + "FROM Material ma "
	        + "LEFT JOIN ma.materialStock ms "
	        + "LEFT JOIN ms.stockManagement sm "
	        + "ORDER BY ma.materialId")
	List<MaterialListResponseDTO> findMaterialList();
	
	@Query("SELECT new himedia.project.highfourm.dto.material.MaterialListResponseDTO("
			+ "ma.materialId, ma.materialName, ma.unit, sm.managementName, ms.totalStock, ms.safetyStock, ms.maxStock, ms.leadTime) "
			+ "FROM Material ma "
			+ "LEFT JOIN ma.materialStock ms "
			+ "LEFT JOIN ms.stockManagement sm "
			+ "WHERE ma.materialId like %:materialId% "
			+ "ORDER BY ma.materialId")
	List<MaterialListResponseDTO> findMaterialByMaterialId(@Param("materialId") String materialId);
	
	@Query("SELECT new himedia.project.highfourm.dto.material.MaterialListResponseDTO("
			+ "ma.materialId, ma.materialName, ma.unit, sm.managementName, ms.totalStock, ms.safetyStock, ms.maxStock, ms.leadTime) "
			+ "FROM Material ma "
			+ "LEFT JOIN ma.materialStock ms "
			+ "LEFT JOIN ms.stockManagement sm "
			+ "WHERE ma.materialName like %:materialName% "
			+ "ORDER BY ma.materialId")
	List<MaterialListResponseDTO> findMaterialByMaterialName(@Param("materialName") String materialName);
	
	@Query("SELECT new himedia.project.highfourm.dto.material.MaterialListResponseDTO("
			+ "ma.materialId, ma.materialName, ma.unit, sm.managementName, ms.totalStock, ms.safetyStock, ms.maxStock, ms.leadTime) "
			+ "FROM Material ma "
			+ "LEFT JOIN ma.materialStock ms "
			+ "LEFT JOIN ms.stockManagement sm "
			+ "WHERE sm.managementName like %:manageName% "
			+ "ORDER BY ma.materialId")
	List<MaterialListResponseDTO> findMaterialByManagement(@Param("manageName") String managementName);
}
