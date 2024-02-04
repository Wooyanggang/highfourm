package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.project.highfourm.dto.WorkPerfomanceListDTO;
import himedia.project.highfourm.entity.WorkPerfomance;

public interface WorkPerfomanceRepository extends JpaRepository<WorkPerfomance, Long>{
	@Query("SELECT NEW himedia.project.highfourm.dto.WorkPerfomanceListDTO(wf.workPerfomanceId, " +
	        "pp.productionPlanId, wf.workDate, pp.product.productId, pp.product.productName, wf.productionAmount, " +
	        "wf.acceptanceAmount, wf.defectiveAmount, wf.workingTime, wf.manager, wf.lotNo, wf.validDate, wf.note) " +
	        "FROM WorkPerfomance wf " +
	        "JOIN wf.productionPlan pp")
	List<WorkPerfomanceListDTO> findList();
}
