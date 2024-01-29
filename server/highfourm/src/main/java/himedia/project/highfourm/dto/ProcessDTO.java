package himedia.project.highfourm.dto;

import lombok.Data;

@Data
public class ProcessDTO {
	private String processId;
	private String productId;
	private Long sequence;
	private String progressName;
	private String timeUnit;
	private Long standardTime;
	private String outputUnit;
}
