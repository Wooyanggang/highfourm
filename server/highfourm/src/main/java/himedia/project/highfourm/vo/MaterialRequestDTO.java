package himedia.project.highfourm.vo;

import lombok.AllArgsConstructor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
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
