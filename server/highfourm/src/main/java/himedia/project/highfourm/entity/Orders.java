package himedia.project.highfourm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter @Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Orders {
	@Id
	@Column(name = "order_id", unique = true)
	private String orderId;
	
	@Column(name = "vendor")
	private String vendor;
	
	@Column(name = "manager")
	private String manager; 
	
	@Column(name = "due_date")
	private String dueDate; 
	
	@Column(name = "ending_state")
	private Boolean endingState;
	
	@Column(name = "order_date")
	private String orderDate;

	@Builder
	public Orders(String orderId, String vendor, String manager, String dueDate, Boolean endingState,
			String orderDate) {
		super();
		this.orderId = orderId;
		this.vendor = vendor;
		this.manager = manager;
		this.dueDate = dueDate;
		this.endingState = endingState;
		this.orderDate = orderDate;
	}
}