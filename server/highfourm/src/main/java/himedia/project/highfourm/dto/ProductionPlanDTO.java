package himedia.project.highfourm.dto;

import lombok.Data;

@Data
public class ProductionPlanDTO {
	private String productionPlanId;
	private String productId;
	private String orderId;
	private String productionUnit;
	private Long productionAmount;
	private String productionStartDate;
	private String dueDate;
}
