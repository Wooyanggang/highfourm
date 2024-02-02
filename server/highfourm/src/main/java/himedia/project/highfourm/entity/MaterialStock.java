package himedia.project.highfourm.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id", referencedColumnName = "material_id")
	private Material material;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "management_id", referencedColumnName = "management_id")
	private StockManagement stockManagement;
	
	@Column(name = "total_stock")
	private Long totalStock;
	
	@Column(name = "safety_stock")
	private Long safetyStock;
	
	@Column(name = "max_stock")
	private Long maxStock;
	
	@Column(name = "lead_time")
	Integer leadTime;
	
	@Builder
	public MaterialStock(String materialId, Material material, StockManagement stockManagement, Long totalStock,
			Long safetyStock, Long maxStock, int leadTime) {
		this.materialId = materialId;
		this.material = material;
		this.stockManagement = stockManagement;
		this.totalStock = totalStock;
		this.safetyStock = safetyStock;
		this.maxStock = maxStock;
		this.leadTime = leadTime;
	}
	
	public void updateMaterialStock(Long materialInventory) {
		this.totalStock = materialInventory;
	}
}