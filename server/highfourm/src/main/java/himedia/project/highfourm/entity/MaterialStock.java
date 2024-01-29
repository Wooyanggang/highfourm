package himedia.project.highfourm.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "material_stock")
public class MaterialStock {
	
	@Id
	@Column(name = "material_id")
	private String materialId;
	
	@Column(name = "method_id")
	private Long methodId;
	
	@Column(name = "total_stock")
	private Long totalStock;
	
	@Column(name = "safety_stock")
	private Long safetyStock;
	
	@Column(name = "max_stock")
	private Long maxStock;
	
	@Column(name = "lead_time")
	int leadTime;
}
