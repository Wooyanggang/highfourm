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
@Table(name = "method")
public class Method {
	@Id
	@Column(name = "method_id")
	private Long methodId;
	
	@Column(name = "method_name", length=10 )
	private String methodName;
}