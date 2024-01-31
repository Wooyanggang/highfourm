package himedia.project.highfourm.dto.material;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MaterialHistoryDTO {
	private String materialHistoryId;
	private Long materialId;
	private String orderDate;
	private String recievingDate;
	private String standard;
	private String supplier;
	private Long materialInventory;
	private Long inboundAmount;
	private String orderAmount;
	private String unitPrice;
	private String note;
}
