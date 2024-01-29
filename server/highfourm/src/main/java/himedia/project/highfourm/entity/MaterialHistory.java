package himedia.project.highfourm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "material_history")
public class MaterialHistory {
	@Id
	//@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id", referencedColumnName = "material_id")
	private String materialHistoryId;
	
	//@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id", referencedColumnName = "material_id")
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
