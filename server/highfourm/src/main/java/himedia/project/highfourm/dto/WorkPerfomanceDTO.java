package himedia.project.highfourm.dto;

import lombok.Data;

@Data
public class WorkPerfomanceDTO {
	private Long workPerformenceNo;
	private String productionPlanId;
	private String workingDate;
	private Long productionAmount;
	private Long acceptanceAmount;
	private Long defectiveAmount;
	private Long workingTime;
	private String manager;
	private Long lotNo;
	private String validDate;
	private String note;
}
