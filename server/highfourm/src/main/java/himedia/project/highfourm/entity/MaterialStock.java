package himedia.project.highfourm.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "material_stock")
public class MaterialStock {
	@Id
	@Column(name = "material_id")
	private String materialId;
	
	@Column(name = "management_id")
	private StockManagement stockManagement;
	
	@Column(name = "total_stock")
	private Long totalStock;
	
	@Column(name = "safety_stock")
	private Long safetyStock;
	
	@Column(name = "max_stock")
	private Long maxStock;
	
	@Column(name = "lead_time")
	int leadTime;
	
	@Builder
	public MaterialStock(String materialId, Material material, StockManagement stockManagement, Long totalStock,
			Long safetyStock, Long maxStock, int leadTime) {
		this.materialId = materialId;
		this.stockManagement = stockManagement;
		this.totalStock = totalStock;
		this.safetyStock = safetyStock;
		this.maxStock = maxStock;
		this.leadTime = leadTime;
	}
	


	
}