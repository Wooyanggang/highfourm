package himedia.project.highfourm.dto;

import himedia.project.highfourm.entity.pk.OrderDetailPk;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;

public class OrderDetailDTO {
	private String orderId;
	private String productId;
	private Long productAmount;
	private Long unitPrice;
}
