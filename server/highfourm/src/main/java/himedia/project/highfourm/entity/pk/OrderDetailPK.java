package himedia.project.highfourm.entity.pk;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "order_id")
	private String orderId;
	
	@Column(name = "product_id")
	private String productId;
}