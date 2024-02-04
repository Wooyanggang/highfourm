package himedia.project.highfourm.entity;

import himedia.project.highfourm.entity.pk.MonthlyProductionPlanPK;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor
@Entity
@Table(name = "monthly_production_plan")
public class MonthlyProductionPlan {
	@EmbeddedId
	private MonthlyProductionPlanPK monthlyProductPlanPK;
	
	@Column(name = "production_amount")
	private Long productionAmount;
	
	@MapsId("productionPlanId")
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "production_plan_id", referencedColumnName = "production_plan_id")
	private ProductionPlan productionPlan;

	@Builder
	public MonthlyProductionPlan(MonthlyProductionPlanPK monthlyProductPlanPK, Long productionAmount,
			ProductionPlan productionPlan) {
		this.monthlyProductPlanPK = monthlyProductPlanPK;
		this.productionAmount = productionAmount;
		this.productionPlan = productionPlan;
	}
}