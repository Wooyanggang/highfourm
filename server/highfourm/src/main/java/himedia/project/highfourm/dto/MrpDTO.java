package himedia.project.highfourm.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MrpDTO {
	
	private String dueDate;
	private String productionPlanId;
	private String productId;
	private String productName;
	private Long productionPlanAmount;
	
	private String materialName;
	private String materialId;
	private Long inputAmount;
	private Long totalMaterialAmount;
	private Long totalStock;
	private Long safetyStock;
	private Long inboundAmount;
	
	@Builder
	public MrpDTO(String dueDate, String productionPlanId, String productId, String productName,
			Long productionPlanAmount) {
		this.dueDate = dueDate;
		this.productionPlanId = productionPlanId;
		this.productId = productId;
		this.productName = productName;
		this.productionPlanAmount = productionPlanAmount;
	}

	@Builder
	public MrpDTO(String materialName, String materialId, Long inputAmount, Long totalMaterialAmount, Long totalStock,
			Long safetyStock, Long inboundAmount) {
		this.materialName = materialName;
		this.materialId = materialId;
		this.inputAmount = inputAmount;
		this.totalMaterialAmount = totalMaterialAmount;
		this.totalStock = totalStock;
		this.safetyStock = safetyStock;
		this.inboundAmount = inboundAmount;
	}
	
	
	
	
	
}
