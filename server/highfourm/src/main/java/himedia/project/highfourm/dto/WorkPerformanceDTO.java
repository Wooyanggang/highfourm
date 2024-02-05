package himedia.project.highfourm.dto;

import java.time.LocalDate;

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
	private LocalDate workDate;
	private Long productionAmount;
	private Long acceptanceAmount;
	private Long defectiveAmount;
	private Long workingTime;
	private String manager;
	private Long lotNo;
	private LocalDate validDate;
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
	
	public WorkPerformanceDTO(String productionPlanId, LocalDate workDate, Long productionAmount, Long acceptanceAmount,
			Long defectiveAmount, Long workingTime) {
		this.productionPlanId = productionPlanId;
		this.workDate = workDate;
		this.productionAmount = productionAmount;
		this.acceptanceAmount = acceptanceAmount;
		this.defectiveAmount = defectiveAmount;
		this.workingTime = workingTime;
	}
}
