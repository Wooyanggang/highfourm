package himedia.project.highfourm.dto;

import java.time.LocalDate;
import java.util.List;

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
public class PerformanceDTO {
	private String productionPlanId;
	private String productId;
	private String productName;
	private String orderId;
	private LocalDate productionStartDate;
	private Long productionPlanAmount;
	private String vendor;
	private String manager;
	private LocalDate orderDate;
	private LocalDate dueDate;
	private Boolean endingState;
	private Long productAmount;
	private Long totalProductionAmount;
	private List<WorkPerformanceDTO> workPerformances;
	
	public PerformanceDTO(String productionPlanId,LocalDate productionStartDate,Long productionPlanAmount, String orderId
			,String vendor, String manager,LocalDate orderDate, LocalDate dueDate, Boolean endingState
			,String productId, String productName,Long productAmount) {
		this.productionPlanId = productionPlanId;
		this.productionStartDate = productionStartDate;
		this.productionPlanAmount = productionPlanAmount;
		this.orderId = orderId;
		this.vendor = vendor;
		this.manager = manager;
		this.orderDate = orderDate;
		this.dueDate = dueDate;
		this.endingState = endingState;
		this.productId = productId;
		this.productName = productName;
		this.productAmount = productAmount;
	}
	
}
