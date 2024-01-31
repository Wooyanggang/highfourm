package himedia.project.highfourm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "product")
public class Product {
	@Id
	@Column(name = "product_id", unique = true)
	private String productId;
	
	@Column(name = "product_name", unique = true)
	private String productName;
	
	@Column(name = "write_date")
	private String writeDate;
	
	@Column(name = "update_date")
	private String updateDate;

	@Builder
	public Product(String productId, String productName, String writeDate, String updateDate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
	}
}