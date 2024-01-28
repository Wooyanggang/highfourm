package himedia.project.highfourm.entity;

import himedia.project.highfourm.entity.pk.OrderDetailPk;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail {
	@EmbeddedId
	private OrderDetailPk orderDetailPk;
	@Column(name = "product_amount")
	private Long productAmount;
	@Column(name = "unit_price")
	private Long unitPrice;
}
