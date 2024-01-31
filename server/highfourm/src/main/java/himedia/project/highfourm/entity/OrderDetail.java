package himedia.project.highfourm.entity;

import himedia.project.highfourm.entity.pk.OrderDetailPk;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail {
	@EmbeddedId
	private OrderDetailPk orderDetailPk;

	@Column(name = "product_amount")
	private Long productAmount;

	@Column(name = "unit_price")
	private Long unitPrice;

	@MapsId("orderId") 
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private Orders orders;

	@MapsId("productId") 
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Product product;
}
