package himedia.project.highfourm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WorkPerfomanceListDTO {
	private Long workPerfomanceId;
	private String productionPlanId;
	private String workDate;
	private String productId;
	private String productName;
	private Long productionAmount;
	private Long acceptanceAmount;
	private Long defectiveAmount;
	private Long workingTime;
	private String manager;
	private Long lotNo;
	private String validDate;
	private String note;
}
