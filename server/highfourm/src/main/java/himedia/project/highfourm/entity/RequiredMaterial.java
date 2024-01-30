package himedia.project.highfourm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "required_material")
public class RequiredMaterial {
	@Id
	@Column(name = "product_id")
	private String productId;
	
	@MapsId
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id", unique = true)
	private Product product;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "material_id", referencedColumnName = "material_id", unique = true)
	private Material material;
	
	@Column(name = "input_process")
	private String inputProcess;
	
	@Column(name = "input_amount")
	private String inputAmount;
}
