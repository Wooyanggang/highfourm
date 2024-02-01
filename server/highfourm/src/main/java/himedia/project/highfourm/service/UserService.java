package himedia.project.highfourm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import himedia.project.highfourm.dto.user.UserDTO;
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
	
	
//	public List<UserDTO> toListDTO(List<User> userlist) {
//		return userlist.stream()
//				.map(user -> UserDTO.builder()
//						.userNo(user.getUserNo())
//						.userName(user.getUserName())
//						.empNo(user.getEmpNo())
//						.position(user.getPosition())
//						.birth(user.getBirth())
//						.email(user.getEmail())
//						.registerState(user.getRegisterState())
//						.build()
//						).collect(Collectors.toList());
//	}
	
	public List<UserDTO> findAllUsers() {
		List<User> userlist = repository.findAll();
		Company company = companyRepository.findById(1L).get();
		
		List<UserDTO> result = 
				userlist.stream().map(user -> user.toDTO(company))
				.collect(Collectors.toList());
		
		return result;
	}
	
	public UserDTO findByUserNo(@RequestParam Long userNo) {
		User user = repository.findById(userNo).get();
		Company company = companyRepository.findById(1L).get();
		log.info(user.getUserName());
		return user.toDTO(company);
	}
	
	public List<UserDTO> findByEmpNo(Long empNo) {
		List<User> userlist = repository.findByEmpNo(empNo);
		Company company = companyRepository.findById(1L).get();
		
		return userlist.stream()
				.map(user -> user.toDTO(company))
				.collect(Collectors.toList());
	}
	
	public List<UserDTO> findByUserName(String name) {
		List<User> userlist = repository.findByUserName(name);
		Company company = companyRepository.findById(1L).get();
		
		return userlist.stream()
				.map(user -> user.toDTO(company))
				.collect(Collectors.toList());
	}
	
	public List<UserDTO> findByEmail(String email) {
		List<User> userlist = repository.findByEmail(email);
		Company company = companyRepository.findById(1L).get();
		
		return userlist.stream()
				.map(user -> user.toDTO(company))
				.collect(Collectors.toList());
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

	public void delete(Long userNo) {
		repository.deleteById(userNo);
	}
	
	public void update(Long userNo) {
		
	}
}
