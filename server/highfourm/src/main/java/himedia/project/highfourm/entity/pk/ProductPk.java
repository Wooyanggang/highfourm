package himedia.project.highfourm.entity.pk;

import java.io.Serializable;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Embeddable
@Data
public class ProductPk implements Serializable{
	@Column(name = "product_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productNo;
	@Column(name = "product_id", unique = true)
	private String productId;

}
