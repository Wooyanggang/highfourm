package himedia.project.highfourm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import himedia.project.highfourm.dto.user.UserDTO;
import himedia.project.highfourm.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@RestController
@Controller
@RequiredArgsConstructor
//@RequestMapping("/users")
public class UserController {
	
	private final UserService service;
	
	@GetMapping("/users")
	public List<UserDTO> selectUserList() {
		return service.findAllUsers();
	}
	
	@GetMapping("/users/search")
	public List<UserDTO> searchUserList(@RequestParam String searchType, @RequestParam String search) {
		List<UserDTO> result = new ArrayList<UserDTO>();
		
		if(searchType.equals("사원명")) {
			result = service.findByUserName(search);
		} else if(searchType.equals("사번")) {
			result = service.findByEmpNo(Long.parseLong(search));
		} else if(searchType.equals("이메일")) {
			result = service.findByEmail(search);
		}
		
		return result;
	}
	
	@GetMapping("/users/new")
	public String addForm() {
		return "userForm";
	}
	
	@PostMapping("/users/new")
	public String addNewUser(@RequestBody UserDTO userDTO, HttpSession session) {
//		Long adminCompanyId = (Long)session.getAttribute("companyId");
//		service.save(userDTO, adminCompanyId);
		service.save(userDTO);
		log.info("session ? " + session.getAttribute("companyId"));
		
		return "redirect:http://localhost:3000/users";
	}
	
	@GetMapping("/users/edit")
	public String selectUser(@RequestParam("empNo") Long empNo, Model model) {
		log.info("empNo : ", empNo);
		UserDTO user = service.findByUserNo(empNo);
		model.addAttribute("user", user);
		return "userEditForm";
	}

	@PutMapping("/users/edit/{empNo}")
	public String updateByUserNo() {
		return "redirect:/users/edit";
	}
	
	@DeleteMapping("/users/delete/{deleteUserNo}")
	public String deleteByUserNo(@RequestParam String deleteUserNo) {
		Long userNo = Long.parseLong(deleteUserNo);
		
		log.info("delete : " + deleteUserNo);
		
		return "redirect:http://localhost:3000/users";
	}
	
}
