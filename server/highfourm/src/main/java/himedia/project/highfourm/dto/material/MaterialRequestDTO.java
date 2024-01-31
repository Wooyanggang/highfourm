package himedia.project.highfourm.dto.material;

import himedia.project.highfourm.entity.Material;
import himedia.project.highfourm.entity.MaterialStock;
import himedia.project.highfourm.entity.StockManagement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder @Getter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialRequestDTO {
    private String materialId;
    private String materialName;
    private String unit;
    private int managementId;
    private Long totalStock;
    private Long safetyStock;
    private Long maxStock;
    private int leadTime;

    public Material toEntityFirst() {
        return Material.builder()
                .materialId(materialId)
                .materialName(materialName)
                .unit(unit)
                .build();
    }
    
    public MaterialStock toEntitySecond(StockManagement stockManagement) {
    	return MaterialStock.builder()
    			.materialId(materialId)
    			.stockManagement(stockManagement)
    			.totalStock(totalStock)
    			.safetyStock(safetyStock)
    			.maxStock(maxStock)
    			.leadTime(leadTime)
    			.build();
    }
}