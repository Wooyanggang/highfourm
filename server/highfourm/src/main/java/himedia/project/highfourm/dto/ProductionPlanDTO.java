package himedia.project.highfourm.dto;

import java.time.LocalDate;

import himedia.project.highfourm.entity.Orders;
import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.entity.ProductionPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductionPlanDTO {
	private String productionPlanId;
	private String productId;
	private String orderId;
	private String productionUnit;
	private Long productionPlanAmount;
	private LocalDate productionStartDate;
	
	public ProductionPlan toEntity(Product product, Orders orders) {
		return ProductionPlan.builder()
				.productionPlanId(productionPlanId)
				.product(product)
				.orders(orders)
				.productionUnit(productionUnit)
				.productionPlanAmount(productionPlanAmount)
				.productionStartDate(productionStartDate)
				.build();
				
	}
}