package himedia.project.highfourm.entity;

import himedia.project.highfourm.dto.ProductionPlanDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "production_plan")
public class ProductionPlan {
	@Id
	@Column(name = "production_plan_id", unique = true)
	private String productionPlanId;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id", unique = true)
	private Product product;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private Orders orders;
	
	@Column(name = "production_unit")
	private String productionUnit;
	
	@Column(name = "production_plan_amount")
	private Long productionPlanAmount;
	
	@Column(name = "production_start_date")
	private String productionStartDate;
	
	public ProductionPlanDTO toDTO(Product product, Orders orders) {
		return ProductionPlanDTO.builder()
					.productionPlanId(productionPlanId)
					.product(product)
					.orders(orders)
					.productionUnit(productionUnit)
					.productionStartDate(productionStartDate)
					.build();
	}
}
