package himedia.project.highfourm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
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
}
