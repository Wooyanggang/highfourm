package himedia.project.highfourm.dto;

import himedia.project.highfourm.entity.ProductionPlan;
import himedia.project.highfourm.entity.WorkPerformance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WorkPerformanceDTO {
	private Long workPerformanceId;
	private String productionPlanId;
	private String workDate;
	private Long productionAmount;
	private Long acceptanceAmount;
	private Long defectiveAmount;
	private Long workingTime;
	private String manager;
	private Long lotNo;
	private String validDate;
	private String note;
	
	public WorkPerformance toEntity(ProductionPlan productionPlan) {
		return WorkPerformance
				.builder()
				.workPerformanceId(workPerformanceId)
				.productionPlan(productionPlan)
				.workDate(workDate)
				.productionAmount(productionAmount)
				.acceptanceAmount(acceptanceAmount)
				.defectiveAmount(defectiveAmount)
				.workingTime(workingTime)
				.manager(manager)
				.lotNo(lotNo)
				.validDate(validDate)
				.note(note)
				.build();
	}
}
