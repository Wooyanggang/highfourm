package himedia.project.highfourm.dto;

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
public class ProductDTO {
	private String productId;
	private String productName;
	private String writeDate;
	private String updateDate;
	
	public Product toEntity() {
		return Product
				.builder()
				.productId(productId)
				.productName(productName)
				.writeDate(writeDate)
				.updateDate(updateDate)
				.build();
	}
}