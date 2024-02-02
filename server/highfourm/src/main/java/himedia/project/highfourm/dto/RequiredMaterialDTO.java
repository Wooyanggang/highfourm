package himedia.project.highfourm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequiredMaterialDTO {

	private String productId;
	private String materialId;
	private String inputProcess;
	private Long inputAmount;
	
}
