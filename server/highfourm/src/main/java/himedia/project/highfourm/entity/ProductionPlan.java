package himedia.project.highfourm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "production_plan")
public class ProductionPlan {
	@Id
	@Column(name = "production_plan_id", unique = true)
	private String productionPlanId;
//	@OneToOne(cascade = CascadeType.REFRESH)
//	@JoinColumn(name = "product_id", referencedColumnName = "product_id", unique = true)
	private String productId;
//	@OneToOne(cascade = CascadeType.REFRESH)
//	@JoinColumn(name = "order_id", referencedColumnName = "order_id", unique = true)
	private String orderId;
	@Column(name = "production_unit")
	private String productionUnit;
	@Column(name = "production_amount")
	private Long productionAmount;
	@Column(name = "production_start_date")
	private String productionStartDate;
	@Column(name = "due_date")
	private String dueDate;
}
