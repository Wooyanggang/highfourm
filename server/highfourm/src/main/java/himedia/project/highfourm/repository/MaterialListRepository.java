package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import himedia.project.highfourm.dto.material.MaterialResponseDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MaterialListRepository {
	
	private final EntityManager em;

    public List<MaterialResponseDTO> findMaterialList(){
		String sql = "SELECT ma.materialId, ma.materialName, ma.unit, sm.managementName, ms.totalStock, ms.safetyStock, ms.maxStock, ms.leadTime "
				+ "FROM Material ma "
				+ "LEFT JOIN ma.materialStock ms "
				+ "LEFT JOIN ms.stockManagement sm "
				+ "ORDER BY ma.materialId";

		List<MaterialResponseDTO> result = em.createQuery(sql, MaterialResponseDTO.class).getResultList();
		log.info("result1 : {}", result);
		
		return result;
    };
}
