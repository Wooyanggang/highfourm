package himedia.project.highfourm.dto;

import lombok.Data;

@Data
public class UserDTO {
	
	private Long userNo;
	private String userId;
	private String password;
	private String userName;
	private Long empNo;
	private String position;
	private String birth;
	private String email;
	private Long companyId;
	private String registerState;
	private String role;
	
}
