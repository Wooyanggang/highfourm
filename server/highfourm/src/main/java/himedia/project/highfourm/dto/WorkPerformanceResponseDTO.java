package himedia.project.highfourm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkPerformanceResponseDTO {
	private String ProductionPlanId;
	private String ProductId;
	private String ProductName;
	
}
