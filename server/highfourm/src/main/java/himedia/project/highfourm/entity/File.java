package himedia.project.highfourm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "file")
public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "file_id")
	private Long fileId;
	@Column(name = "order_id")
	private String orderId;
	@Column(name = "original_name")
	private String originalName;
	@Column(name = "changed_name")
	private String changedName;
	@Column(name = "file_type")
	private String fileType;
	@Column(name = "file_size")
	private String fileSize;
	@Column(name = "file_path")
	private String filePath;
}
