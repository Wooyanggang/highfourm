package himedia.project.highfourm.entity.pk;

import java.io.Serializable;

import himedia.project.highfourm.entity.ProductionPlan;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyProductPlanPK implements Serializable{
	@Column(name = "month")
	private Long month;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "production_plan_id", referencedColumnName = "production_plan_id")
	private ProductionPlan productionPlan;
}
