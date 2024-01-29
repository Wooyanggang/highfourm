package himedia.project.highfourm.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class Orders {
	private String orderId;
	private String vendor;
	private String manager; 
	private String product; 
	private String dueDate; 
	private Boolean endingState; 
	private String orderDate;

}
