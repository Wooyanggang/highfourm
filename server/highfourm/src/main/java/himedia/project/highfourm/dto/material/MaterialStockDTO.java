package himedia.project.highfourm.dto.material;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class MaterialStockDTO {
	private Long material;
	private Long methodId;
	private Long totalStock;
	private Long safetyStock;
	private Long maxStock;
	int leadTime;
}
