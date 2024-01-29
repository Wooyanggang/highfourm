package himedia.project.highfourm.dto;

import himedia.project.highfourm.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private String birth;
	private String email;
	private Company company;
	private String registerState;
	private String role;
	
}
