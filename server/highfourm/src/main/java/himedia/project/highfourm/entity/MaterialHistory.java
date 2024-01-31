package himedia.project.highfourm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "material_history")
public class MaterialHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long materialHistoryId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id", referencedColumnName = "material_id")
	private Material material;
	
	@Column(name = "order_date")
	private String orderDate;
	
	@Column(name = "recieving_date")
	private String recievingDate;
	
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
