package himedia.project.highfourm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.UserDTO;
import himedia.project.highfourm.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	
	@GetMapping("/users")
	public List<UserDTO> selectUserList() {
		return service.findAllUsers();
	}
	
//	@GetMapping("/users/edit/{empNo}")
//	public 

	@PostMapping("/users/new")
	public String userNew(@RequestBody UserDTO userDTO, HttpSession session) {
//		Long adminCompanyId = (Long)session.getAttribute("companyId");
//		service.save(userDTO, adminCompanyId);
		service.save(userDTO);
		log.info("userDTO : " + userDTO.getUserName());
		log.info("userDTO : " + userDTO.getBirth());
		log.info("userDTO : " + userDTO.getPosition());
		log.info("session ? " + session.getAttribute("companyId"));
		
		return "redirect:http://localhost:3000/users";
	}
	
	@PostMapping("/users/edit")
	public String userEdit() {
		return "redirect:/users/edit";
	}
	
	@DeleteMapping("/users/delete/{deleteUserNo}")
	public String deleteUser(@RequestParam String deleteUserNo) {
		Long userNo = Long.parseLong(deleteUserNo);
		
		log.info("delete : " + deleteUserNo);
		
		return "redirect:http://localhost:3000/users";
	}
	
}
