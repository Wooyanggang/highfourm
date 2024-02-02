package himedia.project.highfourm.dto;

import java.time.LocalDate;

import himedia.project.highfourm.entity.Orders;
import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.entity.ProductionPlan;
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
public class ProductionPlanDTO {
	private String productionPlanId;
	private String productId;
	private String orderId;
	private String productionUnit;
	private Long productPlanAmount;
	private LocalDate productionStartDate;
	private String productName;
	private Long productAmount;
	private LocalDate orderDate;
	private LocalDate dueDate;
	private Orders orders;
	private Product product;
	
	public ProductionPlanDTO(Long productAmount, String orderId, LocalDate orderDate, LocalDate dueDate,String productName, String productionPlanId, LocalDate productionStartDate) {
		this.productAmount = productAmount;
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.dueDate = dueDate;
		this.productName = productName;
		this.productionPlanId = productionPlanId;
		this.productionStartDate = productionStartDate;
	}
	
	public ProductionPlan toEntityForInsert(ProductionPlanDTO productionPlanDTO, Orders orders, Product product) {
		return ProductionPlan.builder()
				.productionPlanId(productionPlanDTO.getProductionPlanId())
				.productionUnit(productionPlanDTO.getProductionUnit())
				.orders(orders)
				.product(product)
				.build();
	}
}
