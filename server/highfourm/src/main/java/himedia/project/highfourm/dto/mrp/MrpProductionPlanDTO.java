package himedia.project.highfourm.dto.mrp;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MrpProductionPlanDTO {
	
	private LocalDate dueDate;
	private String productionPlanId;
	private String productId;
	private String productName;
	private Long productionPlanAmount;
	
}
