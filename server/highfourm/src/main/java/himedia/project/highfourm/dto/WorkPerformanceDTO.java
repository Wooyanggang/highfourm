package himedia.project.highfourm.dto;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkPerformanceDTO {
	private Long workPerformenceNo;
	private String productionPlanId;
	private LocalDate date;
	private Long productionAmount;
	private Long acceptanceAmount;
	private Long defectiveAmount;
	private Long workingTime;
	private String manager;
	private Long lotNo;
	private String validDate;
	private String note;
	
	public WorkPerformanceDTO(String productionPlanId, LocalDate date, Long productionAmount, Long acceptanceAmount,
			Long defectiveAmount, Long workingTime) {
		this.productionPlanId = productionPlanId;
		this.date = date;
		this.productionAmount = productionAmount;
		this.acceptanceAmount = acceptanceAmount;
		this.defectiveAmount = defectiveAmount;
		this.workingTime = workingTime;
	}
	
	
}
