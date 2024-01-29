package himedia.project.highfourm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "material")
public class Material {
	@Id
	@Column(name = "material_id")
	private String materialId;
	
	@Column(name = "material_name")
	private String materialName;
	
	private String unit;
}