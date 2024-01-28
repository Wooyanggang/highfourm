package himedia.project.highfourm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "required_material")
public class RequiredMaterial {
	
	@Id
	@OneToMany(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id", unique = true)
	private String productId;
	
	@OneToMany(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "material_id", referencedColumnName = "material_id", unique = true)
	private String materialId;
	
	@Column(name = "process")
	private String process;
	
	@Column(name = "input_unit")
	private String inputUnit;
}
