package himedia.project.highfourm.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.UserDTO;
import himedia.project.highfourm.entity.User;
import himedia.project.highfourm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	private final ModelMapper modelMapper;
	
	@GetMapping("/users")
	public List<UserDTO> users() {
		return service.getAllUsers();
	}

	@PostMapping(value = "/users/new", consumes = "application/json")
	public String userNew(@RequestBody UserDTO userDTO) {
		userDTO.getBirth();
		log.info("getEmpNo : " + userDTO.getEmpNo());
		log.info("getUserName : " + userDTO.getUserName());
		log.info("getEmail : " + userDTO.getEmail());
		log.info("getBirth : " + userDTO.getBirth());
		log.info("getPosition : " + userDTO.getPosition());
		log.info("getRegisterState : " + userDTO.getRegisterState());

		User entity = modelMapper.map(userDTO, User.class);
		service.save(entity);
		
		return "redirect:http://localhost:3000/users";
	}
	
	@PostMapping("/users/edit")
	public String userEdit() {
		return "redirect:/users/edit";
	}
	
}
