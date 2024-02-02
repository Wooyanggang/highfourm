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
public class MaterialOrderEditDTO {
	private String recievingDate;
	private Long inboundAmount;
	private Long materialInventory;
	private String note;
}
