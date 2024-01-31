package himedia.project.highfourm.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class ProductDTO {
	private String productId;
	private String productName;
	private String writeDate;
	private String updateDate;
}
