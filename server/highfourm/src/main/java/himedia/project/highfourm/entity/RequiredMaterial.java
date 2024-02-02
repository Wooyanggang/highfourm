package himedia.project.highfourm.entity;

import himedia.project.highfourm.dto.RequiredMaterialDTO;
import himedia.project.highfourm.entity.pk.RequiredMaterialPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "required_material")
public class RequiredMaterial {
	
	@EmbeddedId
	private RequiredMaterialPK requriedMaterialPK;
	
	@Column(name = "input_process")
	private String inputProcess;
	
	@Column(name = "input_amount")
	private Long inputAmount;
	
	public RequiredMaterialDTO toRequiredMaterialDTO() {
		return RequiredMaterialDTO
				.builder()
				.productId(requriedMaterialPK.getProduct().getProductId())
				.materialId(requriedMaterialPK.getMaterial().getMaterialId())
				.inputProcess(inputProcess)
				.inputAmount(inputAmount)
				.build();
	}
}
