package himedia.project.highfourm.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
@Table
public class User {
	@Id
	private Long userNo;
	private String userId;
	private String password;
	private String userName;
	private Long empNo;
	private String postitioin;
	private String birth;
	private String email;
	private Boolean registerState;
	private String role;
	private Long companyId;
}
