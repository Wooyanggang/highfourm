package himedia.project.highfourm.dto.material;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialOrderEditFormDTO {
	private Long materialHistoryId;
	private String orderDate;
	private String recievingDate;
	private String materialId;
	private String standard;
	private String supplier;
	private Long inboundAmount;	
	private Long orderAmount;
	private Long unitPrice;
	private String note;
}
