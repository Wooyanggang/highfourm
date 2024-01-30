package himedia.project.highfourm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "stock_management")
public class StockManagement {
	@Id
	@Column(name="management_id")
	private int managementId;
	
	@Column(name = "management_name", length=10 )
	private String methodName;

	@Builder
	public StockManagement(int managementId, String methodName) {
		this.managementId = managementId;
		this.methodName = methodName;
	}
}
