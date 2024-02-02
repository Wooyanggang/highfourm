package himedia.project.highfourm.dto.material;

import himedia.project.highfourm.entity.Material;
import himedia.project.highfourm.entity.MaterialHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder @Getter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialOrderRequestDTO {
	private String materialId;
	private String orderDate;
	private String standard;
	private String supplier;
	private Long unitPrice;
	private String recievingDate;
	private Long inboundAmount;	
	private Long orderAmount;
	private String note;
    
	public MaterialHistory toEntity(Material material) {
		return MaterialHistory
				.builder()
				.materialHistoryId(null)
				.material(material)
				.orderDate(orderDate)
				.supplier(supplier)
				.orderAmount(orderAmount)
				.standard(standard)
				.orderAmount(orderAmount)
				.note(note)
				.unitPrice(unitPrice)
				.inboundAmount(inboundAmount)
				.recievingDate(recievingDate)
				.build();
	}
}
