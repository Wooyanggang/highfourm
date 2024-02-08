package himedia.project.highfourm.dto.mrp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MrpRequiredMaterialDTO {

	private String productionPlanId;
	private Long productionPlanAmount;
	private String materialName;
	private String materialId;
	private Long inputAmount;
	private Long totalStock;
	private Long safetyStock;
	private Long orderAmount;
	
}
