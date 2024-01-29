package himedia.project.highfourm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "process")
public class Process {
	@Id
	@Column(name = "process_id", unique = true)
	private String processId;
//	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id", unique = true)	
	private String productId;
	@Column(name = "sequence")
	private Long sequence;
	@Column(name = "progress_name")
	private String progressName;
	@Column(name = "time_unit")
	private String timeUnit;
	@Column(name = "standard_time")
	private Long standardTime;
	@Column(name = "output_unit")
	private String outputUnit;
}
