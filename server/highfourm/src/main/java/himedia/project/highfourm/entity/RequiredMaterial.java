package himedia.project.highfourm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Table(name = "required_material")
public class RequiredMaterial {
	@Id
	private String productId;
	private String materialId;
	private String process;
	private String inputUnit;
}
