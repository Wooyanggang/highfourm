package himedia.project.highfourm.dto.user;

import java.time.LocalDate;

import himedia.project.highfourm.entity.Company;
import himedia.project.highfourm.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private Long userNo;
	private String userId;
	private String password;
	private String userName;
	private Long empNo;
	private String position;
	private LocalDate birth;
	private String email;
	private Company company;
	private String registerState;
	private String role;
	
	public User toEntity(Company company) {
		return User.builder()
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