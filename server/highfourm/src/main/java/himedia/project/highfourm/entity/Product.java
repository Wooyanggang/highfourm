package himedia.project.highfourm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
	@Id
	@Column(name = "product_id")
	private String productId;
	@Column(name = "product_name", unique = true)
	private String productName;
	@Column(name = "write_date")
	private String writeDate;
	@Column(name = "update_date")
	private String updateDate;
}
