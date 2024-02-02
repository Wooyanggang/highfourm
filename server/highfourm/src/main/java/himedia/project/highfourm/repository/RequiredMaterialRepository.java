package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import himedia.project.highfourm.dto.RequiredMaterialDTO;
import himedia.project.highfourm.dto.bom.BomRequiredMaterialDTO;
import himedia.project.highfourm.entity.RequiredMaterial;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RequiredMaterialRepository {
	
	private final EntityManager em;
	
	// save
	@Transactional
	public void save(RequiredMaterial requiredMaterial) {
	    String sql = "INSERT INTO required_material (product_id, material_id, input_process, input_amount) " +
	            "VALUES (:productId, :materialId, :inputProcess, :inputAmount)";

	    em.createNativeQuery(sql)
	            .setParameter("productId", requiredMaterial.getRequriedMaterialPK().getProduct().getProductId())
	            .setParameter("materialId", requiredMaterial.getRequriedMaterialPK().getMaterial().getMaterialId())
	            .setParameter("inputProcess", requiredMaterial.getInputProcess())
	            .setParameter("inputAmount", requiredMaterial.getInputAmount())
	            .executeUpdate();
	}
	
	public List<BomRequiredMaterialDTO> findByProductId(String productId) {
		String sql = "select rm.requriedMaterialPK.material.materialId, rm.inputProcess, rm.requriedMaterialPK.material.materialName, rm.inputAmount " 
	            + "from RequiredMaterial rm "
	            + "where rm.requriedMaterialPK.product.productId like :productId";
		List<BomRequiredMaterialDTO> result = em.createQuery(sql, BomRequiredMaterialDTO.class)
				.setParameter("productId", productId).getResultList();
		return result;
	}
}
