package himedia.project.highfourm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_no")
	private Long userNo;
	
	@Column(name = "user_id")
	private String userId;
	
	private String password;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "emp_no")
	private Long empNo;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "birth")
	private String birth;
	
	@Column(name = "email")
	private String email;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "company_id", referencedColumnName = "company_id")
	private Company company;
	
	@Column(name = "registre_state")
	private String registerState;
	
	@Column(name = "role")
	private String role;
}
