package himedia.project.highfourm.dto.orders;

import himedia.project.highfourm.entity.OrderDetail;
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
public class OrderDetailResponseDTO {
	private String orderId;
	private String productId;
	private Long productAmount;
	private Long unitPrice;
	private String productName;
	
	public static OrderDetailResponseDTO fromEntity(OrderDetail orderDetail) {
        return OrderDetailResponseDTO.builder()
                .orderId(orderDetail.getOrderDetailPk().getOrderId())
                .productId(orderDetail.getOrderDetailPk().getProductId())
                .productAmount(orderDetail.getProductAmount())
                .unitPrice(orderDetail.getUnitPrice())
                .productName(orderDetail.getProduct().getProductName())
                .build();
    }
}
