package himedia.project.highfourm.dto.orders;

import himedia.project.highfourm.entity.OrderDetail;
import himedia.project.highfourm.entity.Orders;
import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.entity.pk.OrderDetailPK;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetailDTO {
	private String orderId;
	private String productId;
	private Long productAmount;
	private Long unitPrice;
	
	public OrderDetail toEntity(OrderDetailDTO orderDetailDTO, Orders orders, Product product) {
	    return OrderDetail.builder()
	            .orderDetailPK(new OrderDetailPK(orderDetailDTO.getOrderId(), orderDetailDTO.getProductId()))
	            .productAmount(orderDetailDTO.getProductAmount())
	            .unitPrice(orderDetailDTO.getUnitPrice())
	            .orders(orders)
	            .product(product)
	            .build();
	}
	
	public static OrderDetailDTO fromEntity(OrderDetail orderDetail) {
	    return OrderDetailDTO.builder()
	            .orderId(orderDetail.getOrderDetailPK().getOrderId())
	            .productId(orderDetail.getOrderDetailPK().getProductId())
	            .productAmount(orderDetail.getProductAmount())
	            .unitPrice(orderDetail.getUnitPrice())
	            .build();
	}
	
	public static OrderDetailDTO fromFormDTO(OrderDetailFormDTO formDTO, String productId) {
	    return OrderDetailDTO.builder()
	            .orderId(formDTO.getOrderId())
	            .productId(productId)
	            .productAmount(formDTO.getProductAmount())
	            .unitPrice(formDTO.getUnitPrice())
	            .build();
	}
}