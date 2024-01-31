package himedia.project.highfourm.dto.orders;


import himedia.project.highfourm.entity.Orders;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrdersDTO {
	private String orderId;
	private String vendor;
	private String manager; 
	private String dueDate; 
	private Boolean endingState;
	private String orderDate;
	
	public Orders convertDTOtoEntity(OrdersDTO ordersDTO) {
	    return Orders.builder()
	            .orderId(ordersDTO.getOrderId())
	            .vendor(ordersDTO.getVendor())
	            .manager(ordersDTO.getManager())
	            .dueDate(ordersDTO.getDueDate())
	            .endingState(ordersDTO.getEndingState())
	            .orderDate(ordersDTO.getOrderDate())
	            .build();
	}
	
   public static OrdersDTO fromEntity(Orders orders) {
        return new OrdersDTO(
                orders.getOrderId(),
                orders.getVendor(),
                orders.getManager(),
                orders.getDueDate(),
                orders.getEndingState(),
                orders.getOrderDate()
        );
    }
}
