package himedia.project.highfourm.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class Orders {
	private String orderId;
	private String vendor;
	private String manager;
	private String product;
	private String dueDate;
	private String orderDate;
	private Boolean endingState;
}
