package himedia.project.highfourm.dto.user;

import java.time.LocalDate;

import himedia.project.highfourm.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDTO {
	
	private String userName;
	private Long empNo;
	private String position;
	private LocalDate birth;
	private String email;
	
	public User toEntity() {
		return User.builder()
				.userName(userName)
				.empNo(empNo)
				.position(position)
				.birth(birth)
				.email(email)
				.build();
	}
	
}