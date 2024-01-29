package himedia.project.highfourm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
=======
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
>>>>>>> feature-issue-18
@Table(name = "required_material")
public class RequiredMaterial {
	
	@Id
	@OneToMany(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id", unique = true)
	private String productId;
	
	@OneToMany(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "material_id", referencedColumnName = "material_id", unique = true)
	private String materialId;
	
	@Column(name = "input_process")
	private String inputProcess;
	
	@Column(name = "input_amount")
	private String inputAmount;
}
