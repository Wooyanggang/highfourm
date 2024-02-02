package himedia.project.highfourm.entity;

import java.time.LocalDate;

import himedia.project.highfourm.dto.user.UserDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_no")
	private Long userNo;
	
	@Column(name = "user_id", unique = true)
	private String userId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "user_name", nullable = false)
	private String userName;
	
	@Column(name = "emp_no", nullable = false)
	private Long empNo;
	
	@Column(name = "position", nullable = false)
	private String position;
	
	@Column(name = "birth", nullable = false)
	private LocalDate birth;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "company_id", referencedColumnName = "company_id", nullable = false)
	private Company company;
	
	@Column(name = "register_state", nullable = false)
	private String registerState;
	
	@Column(name = "role", nullable = false)
	private String role;

	
	public UserDTO toDTO(Company company) {
		return UserDTO.builder()
				.userNo(userNo)
				.userId(userId)
				.password(password)
				.userName(userName)
				.empNo(empNo)
				.position(position)
				.birth(birth)
				.email(email)
				.company(company)
				.registerState(registerState)
				.role(role)
				.build();
	}
}
