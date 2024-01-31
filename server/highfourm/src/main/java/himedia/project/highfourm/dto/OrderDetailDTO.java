package himedia.project.highfourm.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class OrderDetailDTO {
	private String orderId;
	private String productId;
	private Long productAmount;
	private Long unitPrice;
}