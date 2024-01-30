package himedia.project.highfourm.entity;

import java.time.LocalDate;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_no")
	private Long userNo;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "emp_no")
	private Long empNo;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "birth")
	private LocalDate birth;
	
	@Column(name = "email")
	private String email;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "company_id", referencedColumnName = "company_id")
	private Company company;
	
	@Column(name = "register_state")
	private String registerState;
	
	@Column(name = "role")
	private String role;

	@Builder
	public User(Long userNo, String userId, String password, String userName, Long empNo, String position,
			LocalDate birth, String email, Company company, String registerState, String role) {
		this.userNo = userNo;
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.empNo = empNo;
		this.position = position;
		this.birth = birth;
		this.email = email;
		this.company = company;
		this.registerState = registerState;
		this.role = role;
	}

	@Builder
	public User(String userName, Long empNo, String position, LocalDate birth, String email, Company company) {
		this.userName = userName;
		this.empNo = empNo;
		this.position = position;
		this.birth = birth;
		this.email = email;
		this.registerState = "N";
		this.role = "USER";
		
		if(company != null) {
			this.company = company;
		}
	}

	

}
