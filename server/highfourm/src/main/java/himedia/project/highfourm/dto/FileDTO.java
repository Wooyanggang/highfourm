package himedia.project.highfourm.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class FileDTO {
	private Long fileId;
	private String orderId;
	private String originalName;
	private String changedName;
	private String fileType;
	private String fileSize;
	private String filePath;
}
