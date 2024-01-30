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
		String sql = "select plan.due_date, plan.production_plan_id, plan.product_id, p.product_name, plan.production_plan_amount "
				+ "from production_plan plan " + "left join product p on plan.product_id = p.product_id";

		List<MrpDTO> result = em.createQuery(sql, MrpDTO.class).getResultList();
		log.info("result1 : {}", result);
		
		return result;
	}

	public List<MrpDTO> findByMaterials(String productionPlanId) {
		String sql = "select m.material_name, r.material_id, r.input_amount, sum(plan.production_plan_amount*r.input_amount) as total_material_amount, "
				+ "s.total_stock, s.safety_stock, h.inbound_amount " + "from production_plan plan "
				+ "left join required_material r on plan.product_id = r.product_id "
				+ "left join material m on r.material_id = m.material_id "
				+ "left join material_stock s on m.material_id = s.material_id "
				+ "left join material_history h on m.material_id = h.material_id "
				+ "where production_plan_id like :productionPlanId " + "group by production_plan_id";

		List<MrpDTO> result = em.createQuery(sql, MrpDTO.class)
				.setParameter("productionPlanId", productionPlanId).getResultList();

		return result;
	}
	
	public List<MrpDTO> findByProductionPlanID(String productionPlanId) {
		String sql = "select plan.due_date, plan.production_plan_id, plan.product_id, p.product_name, plan.production_plan_amount "
				+ "from production_plan plan "
				+ "left join product p on plan.product_id = p.product_id "
				+ "where production_plan_id like concat('%', :productionPlanId, '%')";
		
		List<MrpDTO> result = em.createQuery(sql, MrpDTO.class)
				.setParameter("productionPlanId", productionPlanId).getResultList();
		
		return result;
	}

	public List<MrpDTO> findByProductId(String productId) {
		String sql = "select plan.due_date, plan.production_plan_id, plan.product_id, p.product_name, plan.production_plan_amount "
				+ "from production_plan plan "
				+ "left join product p on plan.product_id = p.product_id "
				+ "where plan.product_id like concat('%', :productId, '%')";
		
		List<MrpDTO> result = em.createQuery(sql, MrpDTO.class)
				.setParameter("productId", productId).getResultList();
		
		return result;
	}

	public List<MrpDTO> findByProductName(String productName) {
		String sql = "select plan.due_date, plan.production_plan_id, plan.product_id, p.product_name, plan.production_plan_amount "
				+ "from production_plan plan "
				+ "left join product p on plan.product_id = p.product_id "
				+ "where product_name like concat('%', :productName, '%')";
		
		List<MrpDTO> result = em.createQuery(sql, MrpDTO.class)
				.setParameter("productName", productName).getResultList();
		
		return result;
	}
	
	public List<MrpDTO> findByDueDate(String dueDate) {
		String sql = "select plan.due_date, plan.production_plan_id, plan.product_id, p.product_name, plan.production_plan_amount "
				+ "from production_plan plan "
				+ "left join product p on plan.product_id = p.product_id "
				+ "where due_date like concat('%', :dueDate, '%')";
		
		List<MrpDTO> result = em.createQuery(sql, MrpDTO.class)
				.setParameter("dueDate", dueDate).getResultList();
		
		return result;
	}
}
