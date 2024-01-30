package himedia.project.highfourm.dto.material;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialRequestDTO {
	private String materialId;
	private String materialName;
	private String unit;
	private Long totalStock;
	private Long safetyStock;
	private Long maxStock;
	private int leadTime;
	
	public int getLeadTime() {
	    return this.leadTime;
	}
	
}
