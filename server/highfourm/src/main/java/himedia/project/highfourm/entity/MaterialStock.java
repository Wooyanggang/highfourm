package himedia.project.highfourm.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "material_stock")
public class MaterialStock {
	@Id
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id", referencedColumnName = "material_id")
	private String materialId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "method_id", referencedColumnName = "method_id")
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
