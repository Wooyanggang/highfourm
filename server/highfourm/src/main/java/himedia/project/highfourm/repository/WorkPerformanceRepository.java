package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.project.highfourm.dto.WorkPerformanceListDTO;
import himedia.project.highfourm.entity.WorkPerformance;

public interface WorkPerformanceRepository extends JpaRepository<WorkPerformance, Long>{
	@Query("SELECT NEW himedia.project.highfourm.dto.WorkPerformanceListDTO(wf.workPerformanceId, " +
	        "pp.productionPlanId, wf.workDate, pp.product.productId, pp.product.productName, wf.productionAmount, " +
	        "wf.acceptanceAmount, wf.defectiveAmount, wf.workingTime, wf.manager, wf.lotNo, wf.validDate, wf.note) " +
	        "FROM WorkPerformance wf " +
	        "JOIN wf.productionPlan pp")
	List<WorkPerformanceListDTO> findList();
}
