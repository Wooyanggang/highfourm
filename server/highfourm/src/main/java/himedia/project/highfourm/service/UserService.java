package himedia.project.highfourm.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.UserDTO;
import himedia.project.highfourm.entity.User;
import himedia.project.highfourm.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;
	private final ModelMapper modelMapper;
	
	public List<UserDTO> getAllUsers() {
		List<User> userList = repository.findAll();
		
		
		return null;
	}
	
	public User save(User user) {
		return repository.save(user);
	}
	
}
