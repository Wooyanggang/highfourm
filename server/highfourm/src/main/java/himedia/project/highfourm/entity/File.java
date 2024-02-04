package himedia.project.highfourm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "orders", referencedColumnName = "order_id")
	private Orders orders;
	
	@Builder
	public File(Long fileId, String orderId, String originalName, String changedName, String fileType, String fileSize,
			String filePath, Orders orders) {
		super();
		this.fileId = fileId;
		this.orderId = orderId;
		this.originalName = originalName;
		this.changedName = changedName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.filePath = filePath;
		this.orders = orders;
	}
	
	
}