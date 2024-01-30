package himedia.project.highfourm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.UserDTO;
import himedia.project.highfourm.entity.Company;
import himedia.project.highfourm.entity.User;
import himedia.project.highfourm.repository.CompanyRepository;
import himedia.project.highfourm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;
	private final CompanyRepository companyRepository;
	
	private List<UserDTO> convertToDTO(List<User> userlist) {
		List<UserDTO> result = userlist.stream()
								.map(user -> UserDTO.builder()
										.userNo(user.getUserNo())
										.userName(user.getUserName())
										.empNo(user.getEmpNo())
										.position(user.getPosition())
										.birth(user.getBirth())
										.email(user.getEmail())
										.registerState(user.getRegisterState())
										.build()
										).collect(Collectors.toList());
		
		return result;
	}
	
	public List<UserDTO> findAllUsers() {
		
		List<User> user = repository.findAll();
		List<UserDTO> convertedUser = convertToDTO(user);
		
		return convertedUser;
	}
	
	public void save(UserDTO user) {
//		public void save(UserDTO user, Long adminCompanyId) {
//		Optional<Company> company = companyRepository.findById(adminCompanyId);
		Optional<Company> company = companyRepository.findById(1L);
		
		User userEntity = user.toEntity(company.get());
		log.info("userEntity : " + userEntity.getBirth());
		log.info("userEntity : " + userEntity.getUserName());
		
		repository.save(userEntity);
	}

	
}
