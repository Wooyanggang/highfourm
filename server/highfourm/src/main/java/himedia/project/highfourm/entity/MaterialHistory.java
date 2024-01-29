package himedia.project.highfourm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "material_history")
public class MaterialHistory {
	@Id
	@Column(name = "material_history_id")
	private String materialHistoryId;
	
	@Column(name = "material_id")
	private String material_id;
	
	@Column(name = "order_date")
	private String order_date;
	
	@Column(name = "recieving_date")
	private String recieving_date;
	
	private String standard;
	
	private String supplier;
	
	@Column(name = "material_inventory")
	private Long materialInventory;
	
	@Column(name = "inbound_amount")
	private Long inboundAmount;
	
	@Column(name = "order_amount")
	private String orderAmount;
	
	@Column(name = "unit_price")
	private String unitPrice;
	
	private String note;
	
	
	
}
