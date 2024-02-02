package himedia.project.highfourm.dto.material;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Builder
@AllArgsConstructor
public class MaterialOrderListDTO {
	private Long materialHistoryId;
	private String materialId;
	private String orderDate;
	private String recievingDate;
	private String standard;
	private String supplier;
	private Long totalStock;	//이월재고량materialInventory;
	private Long inboundAmount;
	private Long orderAmount;
	private Long unitPrice;
	private String note;
	private String materialName;
	private String unit;
	
}
