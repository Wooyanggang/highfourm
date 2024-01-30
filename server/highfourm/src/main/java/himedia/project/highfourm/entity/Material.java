package himedia.project.highfourm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "material")
public class Material {
	@Id
	@Column(name = "material_id")
	private String materialId;
	
	@Column(name = "material_name")
	private String materialName;
	
	private String unit;
	
    @OneToOne(mappedBy = "material_stock", fetch = FetchType.LAZY)
    private MaterialStock materialStock;
	
	@Builder
	public Material(String materialId, String materialName, String unit) {
		this.materialId = materialId;
		this.materialName = materialName;
		this.unit = unit;
	}
	
	

}
