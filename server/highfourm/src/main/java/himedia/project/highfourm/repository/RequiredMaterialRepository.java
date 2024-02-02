package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import himedia.project.highfourm.dto.bom.BomRequiredMaterialDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RequiredMaterialRepository {
	
	private final EntityManager em;
	
	public List<BomRequiredMaterialDTO> findByProductId(String productId) {
		String sql = "select rm.requriedMaterialPK.material.materialId, rm.inputProcess, rm.requriedMaterialPK.material.materialName, rm.inputAmount " 
	            + "from RequiredMaterial rm "
	            + "where rm.requriedMaterialPK.product.productId like :productId";
		List<BomRequiredMaterialDTO> result = em.createQuery(sql, BomRequiredMaterialDTO.class)
				.setParameter("productId", productId).getResultList();
		return result;
	}
}
