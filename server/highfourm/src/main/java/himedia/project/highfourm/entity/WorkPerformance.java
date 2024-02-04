package himedia.project.highfourm.entity;

import java.time.LocalDate;

import himedia.project.highfourm.dto.WorkPerformanceDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "work_performance")
public class WorkPerformance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_performance_Id")
	private Long workPerformanceId;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "production_plan_id", referencedColumnName = "production_plan_id")
	private ProductionPlan productionPlan;
	
	@Column(name = "work_date")
	private LocalDate workDate;
	
	@Column(name = "production_amount")
	private Long productionAmount;
	
	@Column(name = "acceptance_amount")
	private Long acceptanceAmount;
	
	@Column(name = "defective_amount")
	private Long defectiveAmount;
	
	@Column(name = "working_time")
	private Long workingTime;
	
	@Column(name = "manager")
	private String manager;
	
	@Column(name = "lot_no")
	private Long lotNo;
	
	@Column(name = "valid_date")
	private LocalDate validDate;
	
	@Column(name = "note")
	private String note;
	
	public WorkPerformanceDTO toWorkPerformanceDTO() {
		return WorkPerformanceDTO
				.builder()
				.workPerformanceId(workPerformanceId)
				.productionPlanId(productionPlan.getProductionPlanId())
				.workDate(workDate)
				.productionAmount(productionAmount)
				.acceptanceAmount(acceptanceAmount)
				.defectiveAmount(defectiveAmount)
				.workingTime(workingTime)
				.manager(manager)
				.lotNo(lotNo)
				.validDate(validDate)
				.note(note)
				.build();
	}
}
