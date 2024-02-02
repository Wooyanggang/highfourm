package himedia.project.highfourm.dto.bom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BomRequiredMaterialDTO {
	
	private String materialId;
	private String inputProcess;
	private String materialName;
	private Long inputAmount;

}
