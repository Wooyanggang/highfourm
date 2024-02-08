package himedia.project.highfourm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import himedia.project.highfourm.dto.mrp.MrpProductionPlanDTO;
import himedia.project.highfourm.dto.mrp.MrpRequiredMaterialDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MrpRepository {

	private final EntityManager em;

	public List<MrpProductionPlanDTO> findByProductionPlans() {
		String sql = "select o.dueDate, plan.productionPlanId, plan.product.productId, p.productName, plan.productionPlanAmount "
				+ "from ProductionPlan plan " + "left join plan.product p "
				+ "left join plan.orders o ";

		List<MrpProductionPlanDTO> result = em.createQuery(sql, MrpProductionPlanDTO.class).getResultList();
		
		return result;
	}

	public List<MrpRequiredMaterialDTO> findByMaterials(String productionPlanId) {
		String sql = "select plan.productionPlanId, plan.productionPlanAmount, m.materialName, m.materialId, r.inputAmount, "
				+ "s.totalStock, s.safetyStock, h.orderAmount " + "from ProductionPlan plan "
				+ "left join plan.product p "
				+ "left join RequiredMaterial r on r.requriedMaterialPK.product = p " 
				+ "left join r.requriedMaterialPK.material m "
				+ "left join m.materialStock s "
				+ "left join MaterialHistory h on h.material = m "
				+ "where plan.productionPlanId like :productionPlanId";
		
		List<MrpRequiredMaterialDTO> result = em.createQuery(sql, MrpRequiredMaterialDTO.class)
				.setParameter("productionPlanId", productionPlanId).getResultList();

		return result;
	}
	
	public List<MrpProductionPlanDTO> findByProductionPlanID(String productionPlanId) {
		String sql = "select o.dueDate, plan.productionPlanId, plan.product.productId, p.productName, plan.productionPlanAmount "
				+ "from ProductionPlan plan " + "left join plan.product p "
				+ "left join plan.orders o "
				+ "where plan.productionPlanId like concat('%', :productionPlanId, '%')";
		
		List<MrpProductionPlanDTO> result = em.createQuery(sql, MrpProductionPlanDTO.class)
				.setParameter("productionPlanId", productionPlanId).getResultList();
		
		return result;
	}
	
	public List<MrpProductionPlanDTO> findByProductId(String productId) {
		String sql = "select o.dueDate, plan.productionPlanId, plan.product.productId, p.productName, plan.productionPlanAmount "
				+ "from ProductionPlan plan " + "left join plan.product p "
				+ "left join plan.orders o "
				+ "where p.productId like concat('%', :productId, '%')";
		
		List<MrpProductionPlanDTO> result = em.createQuery(sql, MrpProductionPlanDTO.class)
				.setParameter("productId", productId).getResultList();
		
		return result;
	}

	public List<MrpProductionPlanDTO> findByProductName(String productName) {
		String sql = "select o.dueDate, plan.productionPlanId, plan.product.productId, p.productName, plan.productionPlanAmount "
				+ "from ProductionPlan plan " + "left join plan.product p "
				+ "left join plan.orders o "
				+ "where p.productName like concat('%', :productName, '%')";
		
		List<MrpProductionPlanDTO> result = em.createQuery(sql, MrpProductionPlanDTO.class)
				.setParameter("productName", productName).getResultList();
		
		return result;
	}
	
	public List<MrpProductionPlanDTO> findByDueDate(String dueDate) {
		String sql = "select o.dueDate, plan.productionPlanId, plan.product.productId, p.productName, plan.productionPlanAmount "
				+ "from ProductionPlan plan " + "left join plan.product p "
				+ "left join plan.orders o "
				+ "where plan.dueDate like concat('%', :dueDate, '%')";
		
		List<MrpProductionPlanDTO> result = em.createQuery(sql, MrpProductionPlanDTO.class)
				.setParameter("dueDate", dueDate).getResultList();
		
		return result;
	}
}
