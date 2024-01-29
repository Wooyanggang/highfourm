package himedia.project.highfourm.dto;

import himedia.project.highfourm.entity.Material;
import himedia.project.highfourm.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequiredMaterialDTO {

	private Product product;
	private Material material;
	private String process;
	private String inputUnit;
	
}
