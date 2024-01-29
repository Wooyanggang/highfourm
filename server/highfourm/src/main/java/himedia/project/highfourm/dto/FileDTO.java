package himedia.project.highfourm.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class FileDTO {
	private Long fileId;
	private String orderId;
	private String originalName;
	private String changedName;
	private String fileType;
	private String fileSize;
	private String filePath;
}
