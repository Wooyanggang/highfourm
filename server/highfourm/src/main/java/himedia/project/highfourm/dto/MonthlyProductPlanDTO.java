package himedia.project.highfourm.dto;

import lombok.Data;

@Data
public class MonthlyProductPlanDTO {
	private Long month;
	private String productionPlanId;
	private Long productionAmount;
}
