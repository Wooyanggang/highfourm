package himedia.project.highfourm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.UserDTO;
import himedia.project.highfourm.entity.User;
import himedia.project.highfourm.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;
//	private final ModelMapper modelMapper;
	
	private UserDTO convertDTO(UserDTO userDTO, User user) {
		// DTO to Entity
//		User entity = modelMapper.map(userDTO, User.class);
		
		// Entity to DTO
		
		
		return null;
	}
	
	public List<UserDTO> getAllUsers() {
		List<User> userList = repository.findAll();
		return null;
	}
	
	
	
}
