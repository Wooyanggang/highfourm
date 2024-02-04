package himedia.project.highfourm.dto;

import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.entity.RequiredMaterial;
import himedia.project.highfourm.entity.pk.RequiredMaterialPK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import himedia.project.highfourm.entity.Material;
import himedia.project.highfourm.entity.Process;

@Builder 
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BomRequestDTO {
	private String productId;
	private String productName;
	private String writeDate;
	private String updateDate;
	private String processId;
	private Long sequence;
	private String processName;
	private String timeUnit;
	private Long standardWorkTime;
	private String outputUnit;
	private String materialId;
	private String inputProcess;
	private Long inputAmount;
	
	public Product toProductEntity() {
		return Product.builder()
				.productId(productId)
				.productName(productName)
				.writeDate(writeDate)
				.updateDate(updateDate)
				.build();
	}
	
	public Process toProcessEntity(Product product) {
		return Process.builder()
				.product(product)
				.processId(processId)
				.sequence(sequence)
				.processName(processName)
				.timeUnit(timeUnit)
				.standardWorkTime(standardWorkTime)
				.outputUnit(outputUnit)
				.build();
	}
	
	public RequiredMaterial toRequiredMaterialEntity(Product product, Material material) {
		return RequiredMaterial.builder()
				.requriedMaterialPK(new RequiredMaterialPK(product, material))
				.inputProcess(inputProcess)
				.inputAmount(inputAmount)
				.build();
	}
}
