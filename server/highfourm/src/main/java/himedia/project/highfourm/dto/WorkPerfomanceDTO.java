package himedia.project.highfourm.dto;

import himedia.project.highfourm.entity.ProductionPlan;
import himedia.project.highfourm.entity.WorkPerfomance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WorkPerfomanceDTO {
	private Long workPerfomanceId;
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
	
	public WorkPerfomance toEntity(ProductionPlan productionPlan) {
		return WorkPerfomance
				.builder()
				.workPerfomanceId(workPerfomanceId)
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
