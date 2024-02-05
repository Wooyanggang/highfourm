package himedia.project.highfourm.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductionStatusDTO {
	private String productionPlanId;
	private String productId;
	private String productName;
	private String orderId;
	private Long productionPlanAmount;
	private LocalDate productionStartDate;
	
}