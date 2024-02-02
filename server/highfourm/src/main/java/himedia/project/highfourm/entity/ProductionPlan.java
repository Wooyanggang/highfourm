package himedia.project.highfourm.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor
@Entity
@Table(name = "production_plan")
public class ProductionPlan {
	@Id
	@Column(name = "production_plan_id", unique = true)
	private String productionPlanId;
	
	@Column(name = "production_unit")
	private String productionUnit;
	
	@Column(name = "production_amount")
	private Long productionPlanAmount;
	
	@Column(name = "production_start_date")
	private LocalDate productionStartDate;
	
	@MapsId("productId") 
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Product product;
	
	@MapsId("orderId") 
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private Orders orders;
	
	
	@Builder
	public ProductionPlan(String productionPlanId, String productionUnit, Product product, Orders orders) {
		this.productionPlanId = productionPlanId;
		this.productionUnit = productionUnit;
		this.product = product;
		this.orders = orders;
	}
}
