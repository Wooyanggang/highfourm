package himedia.project.highfourm.entity;

import himedia.project.highfourm.entity.pk.MonthlyProductPlanPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "monthly_product_plan")
public class MonthlyProductPlan {
	@EmbeddedId
	private MonthlyProductPlanPK monthlyProductPlanPK;
	@Column(name = "production_amount")
	private Long productionAmount;
}