package himedia.project.highfourm.entity;

import java.time.LocalDate;
import java.util.List;

import himedia.project.highfourm.dto.ProductionPlanFormDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
// @Builder
@Entity
@NoArgsConstructor
@Table(name = "production_plan")
public class ProductionPlan {
	@Id
	@Column(name = "production_plan_id", unique = true)
	private String productionPlanId;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Product product;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private Orders orders;
	
	@Column(name = "production_unit")
	private String productionUnit;
	
	@Column(name = "production_plan_amount")
	private Long productionPlanAmount;
	
	@Column(name = "production_start_date")
	private LocalDate productionStartDate;
	
	@OneToMany(mappedBy = "productionPlan")
	private List<MonthlyProductionPlan> monthlyProductionPlan;
	
	@OneToMany(mappedBy = "productionPlan")
	private List<WorkPerformance> workPerformances;
	
	public ProductionPlanFormDTO toDTO() {
		return ProductionPlanFormDTO.builder()
					.productionPlanId(productionPlanId)
					.productId(product.getProductId())
					.orderId(orders.getOrderId())
					.productionUnit(productionUnit)
					.productionPlanAmount(productionPlanAmount)
					.productionStartDate(productionStartDate)
					.build();
	}

	@Builder
	public ProductionPlan(String productionPlanId, String productionUnit, Long productionPlanAmount,
			LocalDate productionStartDate, Product product, Orders orders,
			List<MonthlyProductionPlan> monthlyProductionPlan, List<WorkPerformance> workPerformances) {
		this.productionPlanId = productionPlanId;
		this.productionUnit = productionUnit;
		this.productionPlanAmount = productionPlanAmount;
		this.productionStartDate = productionStartDate;
		this.product = product;
		this.orders = orders;
		this.monthlyProductionPlan = monthlyProductionPlan;
		this.workPerformances = workPerformances;
	}
}
