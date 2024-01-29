package himedia.project.highfourm.dto;

import lombok.Data;

@Data
public class WorkPerfomanceDTO {
	private Long workPerformenceNo;
	private String productionPlanId;
	private String date;
	private Long productionAmount;
	private Long acceptancenAmount;
	private Long defectivenAmount;
	private Long workingTime;
	private String manager;
	private Long lotNo;
	private String validDate;
	private String note;
}
