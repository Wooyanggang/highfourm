package himedia.project.highfourm.entity.pk;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Embeddable
@Data
public class MonthlyProductPlanPK implements Serializable{
	@Column(name = "month")
	private Long month;
//	@OneToMany(cascade = CascadeType.REFRESH)
//	@JoinColumn(name = "production_plan_id", referencedColumnName = "production_plan_id")
	private String productionPlanId;
}
