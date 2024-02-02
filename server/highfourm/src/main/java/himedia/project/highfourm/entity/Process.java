package himedia.project.highfourm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "process")
public class Process {
	@Id
	@Column(name = "process_id")
	private String processId;
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")	
	private Product product;
	@Column(name = "sequence")
	private Long sequence;
	@Column(name = "process_name")
	private String processName;
	@Column(name = "time_unit")
	private String timeUnit;
	@Column(name = "standard_work_time")
	private Long standardWorkTime;
	@Column(name = "output_unit")
	private String outputUnit;
}
