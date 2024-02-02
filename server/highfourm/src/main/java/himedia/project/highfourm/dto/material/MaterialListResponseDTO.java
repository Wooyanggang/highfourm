package himedia.project.highfourm.dto.material;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialListResponseDTO{
    private String materialId;
    private String materialName;
    private String unit;
    private String managementName;
    private Long totalStock;
    private Long safetyStock;
    private Long maxStock;
    private Integer leadTime;
	
}
