package himedia.project.highfourm.entity;

import himedia.project.highfourm.entity.pk.MonthlyProductPlanPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "monthly_product_plan")
public class MonthlyProductPlan {
	@EmbeddedId
	private MonthlyProductPlanPK monthlyProductPlanPK;
	@Column(name = "production_amount")
	private Long productionAmount;
}
