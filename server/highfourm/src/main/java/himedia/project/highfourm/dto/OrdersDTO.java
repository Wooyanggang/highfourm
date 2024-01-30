package himedia.project.highfourm.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class OrdersDTO {
	private String orderId;
	private String vendor;
	private String manager; 
	private String dueDate; 
	private Boolean endingState; 
	private String orderDate;

}
