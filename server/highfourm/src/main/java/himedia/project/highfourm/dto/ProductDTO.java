package himedia.project.highfourm.dto;

import lombok.Data;

@Data
public class ProductDTO {
	// composite primary key
	private Long productNo;
	// composite primary key
	private String productId;
	
	private String productName;
	private String writeDate;
	private String updateDate;
}
