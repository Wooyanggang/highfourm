package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import himedia.project.highfourm.dto.bom.BomRequiredMaterialDTO;
import himedia.project.highfourm.entity.RequiredMaterial;
import himedia.project.highfourm.entity.pk.RequiredMaterialPK;
import jakarta.persistence.Tuple;

public interface BomRequiredMaterialRepository extends JpaRepository<RequiredMaterial, RequiredMaterialPK> {
//	public List<BomRequiredMaterialDTO> findByProductId(String productId) {
//		String sql = "select rm.requriedMaterialPK.material.materialId, rm.inputProcess, rm.requriedMaterialPK.material.materialName, rm.inputAmount " 
//	            + "from RequiredMaterial rm "
//	            + "where rm.requriedMaterialPK.product.productId like :productId";
//		List<BomRequiredMaterialDTO> result = em.createQuery(sql, BomRequiredMaterialDTO.class)
//				.setParameter("productId", productId).getResultList();
//		return result;
		
//	List<BomRequiredMaterialDTO> findByRequriedMaterialPK_Product_ProductId(String productId);
	@Query("SELECT rm.requriedMaterialPK.material.materialId AS materialId, " +
	           "rm.inputProcess AS inputProcess, " +
	           "rm.requriedMaterialPK.material.materialName AS materialName, " +
	           "rm.inputAmount AS inputAmount " +
	           "FROM RequiredMaterial rm " +
	           "WHERE rm.requriedMaterialPK.product.productId = :productId")
	    List<Tuple> findBomRequiredMaterial(@Param("productId") String productId);
}
