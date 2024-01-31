package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import himedia.project.highfourm.dto.MrpDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MrpRepository {

	private final EntityManager em;

	public List<MrpDTO> findByProductionPlans() {
		String sql = "select plan.dueDate, plan.productionPlanId, plan.product.productId, p.productName, plan.productionPlanAmount "
				+ "from ProductionPlan plan " + "left join plan.product p";

		List<MrpDTO> result = em.createQuery(sql, MrpDTO.class).getResultList();
		log.info("result1 : {}", result);
		
		return result;
	}

	public List<MrpDTO> findByMaterials(String productionPlanId) {
		String sql = "select m.materialName, r.material.materialId, r.inputAmount, sum(plan.productionPlanAmount*r.inputAmount) as totalMaterialAmount, "
				+ "s.totalStock, s.safetyStock, h.inboundAmount " + "from ProductionPlan plan "
				+ "left join plan.product p "
				+ "left join p.requiredMaterial r "
				+ "left join r.material m "
				+ "left join m.materialStock s "
				+ "left join m.materialHistory h "
				+ "where plan.productionPlanId like :productionPlanId " + "group by plan.productionPlanId";

		List<MrpDTO> result = em.createQuery(sql, MrpDTO.class)
				.setParameter("productionPlanId", productionPlanId).getResultList();

		return result;
	}
	
	public List<MrpDTO> findByProductionPlanID(String productionPlanId) {
		String sql = "select plan.dueDate, plan.productionPlanId, plan.product.productId, p.productName, plan.productionPlanAmount "
				+ "from ProductionPlan plan " + "left join plan.product p"
				+ "where plan.productionPlanId like concat('%', :productionPlanId, '%')";
		
		List<MrpDTO> result = em.createQuery(sql, MrpDTO.class)
				.setParameter("productionPlanId", productionPlanId).getResultList();
		
		return result;
	}

	public List<MrpDTO> findByProductId(String productId) {
		String sql = "select plan.dueDate, plan.productionPlanId, plan.product.productId, p.productName, plan.productionPlanAmount "
				+ "from ProductionPlan plan " + "left join plan.product p"
				+ "where p.productId like concat('%', :productId, '%')";
		
		List<MrpDTO> result = em.createQuery(sql, MrpDTO.class)
				.setParameter("productId", productId).getResultList();
		
		return result;
	}

	public List<MrpDTO> findByProductName(String productName) {
		String sql = "select plan.dueDate, plan.productionPlanId, plan.product.productId, p.productName, plan.productionPlanAmount "
				+ "from ProductionPlan plan " + "left join plan.product p"
				+ "where p.productName like concat('%', :productName, '%')";
		
		List<MrpDTO> result = em.createQuery(sql, MrpDTO.class)
				.setParameter("productName", productName).getResultList();
		
		return result;
	}
	
	public List<MrpDTO> findByDueDate(String dueDate) {
		String sql = "select plan.dueDate, plan.productionPlanId, plan.product.productId, p.productName, plan.productionPlanAmount "
				+ "from ProductionPlan plan " + "left join plan.product p"
				+ "where plan.dueDate like concat('%', :dueDate, '%')";
		
		List<MrpDTO> result = em.createQuery(sql, MrpDTO.class)
				.setParameter("dueDate", dueDate).getResultList();
		
		return result;
	}
}
