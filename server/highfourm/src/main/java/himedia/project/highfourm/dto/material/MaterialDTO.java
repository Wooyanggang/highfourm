package himedia.project.highfourm.dto.material;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class MaterialDTO {
	private String materialId;
	private String materialName;
	private String unit;
}
