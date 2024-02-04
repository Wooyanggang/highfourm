package himedia.project.highfourm.dto;

import himedia.project.highfourm.entity.Orders;
import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.entity.ProductionPlan;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductionPlanDTO {
	private String productionPlanId;
	private Product product;
	private Orders orders;
	private String productionUnit;
	private Long productionAmount;
	private String productionStartDate;
	
	public ProductionPlan toEntity(Product product, Orders orders) {
		return ProductionPlan.builder()
				.productionPlanId(productionPlanId)
				.product(product)
				.orders(orders)
				.productionUnit(productionUnit)
				.productionPlanAmount(productionAmount)
				.productionStartDate(productionStartDate)
				.build();
				
	}
}
