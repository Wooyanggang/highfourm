package himedia.project.highfourm.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.user.UserJoinDTO;
import himedia.project.highfourm.entity.Company;
import himedia.project.highfourm.entity.User;
import himedia.project.highfourm.repository.CompanyRepository;
import himedia.project.highfourm.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService {
	
	private final UserRepository userRepository;
	private final CompanyRepository companyRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public void joinProcess(UserJoinDTO joinDTO) {
		String userId = joinDTO.getUserId();
		String password = joinDTO.getPassword();
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		
		Boolean isExist = userRepository.existsByUserId(userId);
		
		if (isExist) {
			return;
		}
		
		Company company = companyRepository.findById(1L).get();
		
		User user = User.builder()
						.empNo(null)
						.userId(userId)
						.password(encodedPassword)
						.userName(joinDTO.getUserName())
						.empNo(joinDTO.getEmpNo())
						.position("총관리자")
						.birth(null)
						.email("email@google.com")
						.company(company)
						.birth(joinDTO.getBirth())
						.registerState("Y")
						.role("ADMIN")
						.build();
		//Optional<User> user = userRepository.findById(joinDTO.getUserNo());
		
		
		//user.get().joinUser(userId, encodedPassword);
		
		userRepository.save(user);
	}
}
