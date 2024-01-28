package himedia.project.highfourm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "required_material")
public class RequiredMaterial {
	private String productId;
	private String materialId;
	private String process;
	private String inputUnit;
}
