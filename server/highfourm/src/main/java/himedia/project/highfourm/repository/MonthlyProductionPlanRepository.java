package himedia.project.highfourm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.project.highfourm.entity.MonthlyProductionPlan;
import himedia.project.highfourm.entity.pk.MonthlyProductionPlanPK;
import java.util.List;


public interface MonthlyProductionPlanRepository extends JpaRepository<MonthlyProductionPlan, MonthlyProductionPlanPK>{
	
	@Query(value = "SELECT * FROM monthly_production_plan where production_plan_id like ?1 ORDER BY month ASC", nativeQuery = true)
	List<MonthlyProductionPlan> findByProductionPlanId(String productionPlanId);
	
	@Query(value = "INSERT monthly_production_plan values(?1, ?2, ?3)", nativeQuery = true)
	void saveMonthlyProductPlan(String month,String productionPlanId,Long productionAmount);
}
