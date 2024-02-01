package himedia.project.highfourm.dto.material;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Builder
@AllArgsConstructor
public class MaterialOrderResponseDto {
	private Long materialHistoryId;
	private String orderDate;
	private String recievingDate;
	private String materialId;
	private String materialName;
	private String standard;
	private String unit;
	private String supplier;
	private Long restStock;	//이월 재고 -> 입고 등록시 stock 테이블 total_stock 총재고량 ||없으면 0
	private Long materialInventory;	//재고량 ->이월 재고량 total_stock + 입고량	||없으면 0
	private Long usedAmount;	//사용량 1. 이전 재고량(직전material_inventory - 이월 재고량total_stock) ||없으면 0  
	private Long inboundAmount;	
	private Long orderAmount;
	private Integer unitPrice;
	private Long totalPrice;	//총금액
	private String note;
	
    public static MaterialOrderResponseDto toOrderDTO(MaterialOrderListDTO materialOrderListDTO) {
        // 적절한 비즈니스 로직에 따라 값을 설정
        Long restStock = calculateRestStock(materialOrderListDTO);
        Long usedAmount = calculateUsedAmount(materialOrderListDTO);
        Long totalPrice = calculateTotalPrice(materialOrderListDTO);

        return MaterialOrderResponseDto.builder()
                .materialHistoryId(materialOrderListDTO.getMaterialHistoryId())
                .orderDate(materialOrderListDTO.getOrderDate())
                .recievingDate(materialOrderListDTO.getRecievingDate())
                .materialId(materialOrderListDTO.getMaterialId())
                .materialName(materialOrderListDTO.getMaterialName())
                .standard(materialOrderListDTO.getStandard())
                .unit(materialOrderListDTO.getUnit())
                .supplier(materialOrderListDTO.getSupplier())
                .restStock(restStock)
                .materialInventory(materialOrderListDTO.getMaterialInventory())
                .usedAmount(usedAmount)
                .inboundAmount(materialOrderListDTO.getInboundAmount())
                .orderAmount(materialOrderListDTO.getOrderAmount())
                .unitPrice(materialOrderListDTO.getUnitPrice())
                .totalPrice(totalPrice)
                .note(materialOrderListDTO.getNote())
                .build();
    }

    private static Long calculateRestStock(MaterialOrderListDTO orderListDTO) {
    	if (orderListDTO.getInboundAmount() !=null) {
    		return orderListDTO.getInboundAmount()*orderListDTO.getUnitPrice();
    	}
    	return null; 
    }

    private static Long calculateUsedAmount(MaterialOrderListDTO orderListDTO) {
    	if (orderListDTO.getInboundAmount() !=null) {
    		return orderListDTO.getInboundAmount()*orderListDTO.getUnitPrice();
    	}
    	return null;  
    }

    private static Long calculateTotalPrice(MaterialOrderListDTO orderListDTO) {
    	if (orderListDTO.getInboundAmount() !=null) {
    		return orderListDTO.getInboundAmount()*orderListDTO.getUnitPrice();
    	}
    	return null;  
    }
}
