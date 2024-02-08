package himedia.project.highfourm.dto.orders;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import himedia.project.highfourm.entity.Orders;
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
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDate; 
	private Boolean endingState;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate orderDate;
	
	public Orders toEntity(OrdersDTO ordersDTO) {
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