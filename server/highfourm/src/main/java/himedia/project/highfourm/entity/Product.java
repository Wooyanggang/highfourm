package himedia.project.highfourm.entity;

import himedia.project.highfourm.dto.ProductDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "product")
public class Product {
	@Id
	@Column(name = "product_id", unique = true, nullable = false)
	private String productId;
	
	@Column(name = "product_name", nullable = false)
	private String productName;
	
	@Column(name = "write_date", nullable = false)
	private String writeDate;
	
	@Column(name = "update_date")
	private String updateDate;
	
	public ProductDTO toProductDTO() {
		return ProductDTO
				.builder()
				.productId(productId)
				.productName(productName)
				.writeDate(writeDate)
				.updateDate(updateDate)
				.build();
	}
	
	@Builder
	public Product(String productId, String productName, String writeDate, String updateDate) {
		this.productId = productId;
		this.productName = productName;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
	}
}