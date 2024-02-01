package himedia.project.highfourm.entity;

import himedia.project.highfourm.entity.pk.MonthlyProductionPlanPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "monthly_product_plan")
public class MonthlyProductionPlan {
	@EmbeddedId
	private MonthlyProductionPlanPK monthlyProductPlanPK;
	@Column(name = "production_amount")
	private Long productionAmount;
}
