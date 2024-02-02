package himedia.project.highfourm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "material_history")
public class MaterialHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "material_history_id")
	private Long materialHistoryId;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id", referencedColumnName = "material_id", nullable = false)
	private Material material;
	
	@Column(name = "order_date", nullable = false)
	private String orderDate;
	
	@Column(name = "recieving_date")
	private String recievingDate;
	
	@Column(nullable = false)
	private String standard;
	
	@Column(nullable = false)
	private String supplier;
	
	@Column(name = "material_inventory")
	private Long materialInventory;
	
	@Column(name = "inbound_amount")
	private Long inboundAmount;
	
	@Column(name = "order_amount", nullable = false)
	private Long orderAmount;
	
	@Column(name = "unit_price")
	private Long unitPrice;
	
	private String note;
	
	@Builder
	public MaterialHistory(Long materialHistoryId, Material material, String orderDate, String recievingDate,
			String standard, String supplier, Long materialInventory, Long inboundAmount, Long orderAmount,
			Long unitPrice, String note) {
		super();
		this.materialHistoryId = materialHistoryId;
		this.material = material;
		this.orderDate = orderDate;
		this.recievingDate = recievingDate;
		this.standard = standard;
		this.supplier = supplier;
		this.materialInventory = materialInventory;
		this.inboundAmount = inboundAmount;
		this.orderAmount = orderAmount;
		this.unitPrice = unitPrice;
		this.note = note;
	}
	
    public void updateMaterialHistory(Long inboundAmount, Long materialInventory,  String recievingDate, String note) {
        this.materialInventory = materialInventory;
        this.inboundAmount = inboundAmount;
        this.recievingDate = recievingDate;
        this.note = note;
    }

}
