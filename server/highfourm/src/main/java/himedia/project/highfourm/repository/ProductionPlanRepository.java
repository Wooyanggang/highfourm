package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.project.highfourm.dto.ProductionPlanDTO;
import himedia.project.highfourm.entity.ProductionPlan;

public interface ProductionPlanRepository extends JpaRepository<ProductionPlan, String> {
	
	@Query("SELECT new himedia.project.highfourm.dto.ProductionPlanDTO"
			+ "(od.productAmount, o.orderId, o.orderDate, o.dueDate, p.productName, plan.productionPlanId, plan.productionStartDate) " +
		       "FROM OrderDetail od " +
		       "LEFT JOIN od.orders o " +
		       "LEFT JOIN od.product p " +
		       "LEFT JOIN o.productionPlans plan")
		List<ProductionPlanDTO> findAllProductionPlan(Sort sort);
}
