package himedia.project.highfourm.entity;

import himedia.project.highfourm.entity.pk.ProductPk;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "product")
public class Product {
	@EmbeddedId
	private ProductPk productPk;
	@Column(name = "product_name", unique = true)
	private String productName;
	@Column(name = "write_date")
	private String writeDate;
	@Column(name = "update_date")
	private String updateDate;
}
