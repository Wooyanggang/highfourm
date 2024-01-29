package himedia.project.highfourm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.UserDTO;
import himedia.project.highfourm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	
	@GetMapping("/users")
	public List<UserDTO> users() {
		return service.getAllUsers();
	}

	@PostMapping("/users/new")
	public String userNew(@RequestBody UserDTO userDTO) {
//	public Map<String, Object> userNew(@RequestBody HashMap<String, Object> map) {
//		System.out.println(map);
//		Map<String, Object> result = map;
		log.info("getEmpNo : " + userDTO.getEmpNo());
		log.info("getUserName : " + userDTO.getUserName());
		log.info("getEmail : " + userDTO.getEmail());
		log.info("getBirth : " + userDTO.getBirth());
		log.info("getPosition : " + userDTO.getPosition());
		log.info("getRegisterState : " + userDTO.getRegisterState());
		
		return "redirect:/users";
//		return result;
	}
	
	@PostMapping("/users/edit")
	public String userEdit() {
		return "redirect:/users/edit";
	}
	
}
