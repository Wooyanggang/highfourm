package himedia.project.highfourm.dto;

import himedia.project.highfourm.entity.Process;
import himedia.project.highfourm.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProcessDTO {
	private String processId;
	private String productId;
	private Long sequence;
	private String processName;
	private String timeUnit;
	private Long standardWorkTime;
	private String outputUnit;
	
	public Process toEntity(Product product) {
		return Process
				.builder()
				.processId(processId)
				.product(product)
				.sequence(sequence)
				.processName(processName)
				.timeUnit(timeUnit)
				.standardWorkTime(standardWorkTime)
				.outputUnit(outputUnit)
				.build();
				
	}
}
