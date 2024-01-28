package himedia.project.highfourm.dto;

import himedia.project.highfourm.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
public class Orders {
	private String orderId;
	private String vendor;
	private String manager; 
	private String product; 
	private String dueDate; 
	private Boolean endingState; 
	private String orderDate;

}
