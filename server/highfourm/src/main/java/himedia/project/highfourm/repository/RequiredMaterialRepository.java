package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import himedia.project.highfourm.dto.bom.BomRequiredMaterialDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RequiredMaterialRepository {
	
	private EntityManager em;
	
	public List<BomRequiredMaterialDTO> findByMaterialId(String materialId) {
		String sql = "select rm.materialId, rm.inputProcess, m.materialName, rm.inputAmount" 
				+ "from RequiredMaterial rm"
				+ "left join requiredMaterial.Material m"
				+ "where rm.materialId like :materialId";
		List<BomRequiredMaterialDTO> result = em.createQuery(sql, BomRequiredMaterialDTO.class)
				.setParameter("materialId", materialId).getResultList();
		return result;
	}
}
