package himedia.project.highfourm.dto.mrp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MrpRequiredMaterialDTO {

	private String materialName;
	private String materialId;
	private Long inputAmount;
	private Long totalMaterialAmount;
	private Long totalStock;
	private Long safetyStock;
	private Long inboundAmount;
	
}
