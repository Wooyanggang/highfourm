package himedia.project.highfourm.dto.user;

import java.time.LocalDate;

import himedia.project.highfourm.entity.Company;
import himedia.project.highfourm.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 사용자 계정 생성

@Builder
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserJoinDTO {
	
	private Long userNo;
	
	private String userId;
	
	private String email;
	
	private String userName;
	
	private Long empNo;
	
	@NotBlank(message = "비밀번호을 입력해주세요")
	private String password;
	
	@NotBlank(message = "비밀번호 확인을 입력해주세요")
	private String passwordCheck;
	
	private LocalDate birth;
	
//	public User toEntity(Company company) {
//		return User.builder()
//				.userNo(userNo)
//				.userId(userId)
//				.userName(userName)
//				.empNo(empNo)
//				.birth(birth)
//				.email(email)
//				.registerState("y")
//				.role("USER")
//				.build();
//	}
}
