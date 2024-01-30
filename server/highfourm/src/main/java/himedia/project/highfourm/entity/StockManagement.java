package himedia.project.highfourm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "stock_management")
public class StockManagement {
	@Id
	@Column(name="management_id")
	private Long managementId;
	
	@Column(name = "management_name", length=10 )
	private String methodName;
}
