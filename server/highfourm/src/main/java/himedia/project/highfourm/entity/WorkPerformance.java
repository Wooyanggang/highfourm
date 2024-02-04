package himedia.project.highfourm.entity;

import java.time.LocalDate;

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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "work_performance")
public class WorkPerformance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_performance_no")
	private Long workPerformanceNo;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "production_plan_id", referencedColumnName = "production_plan_id")
	private ProductionPlan productionPlan;
	
	@Column(name = "date")
	private LocalDate date;
	
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
	private String validDate;
	
	@Column(name = "note")
	private String note;
}
