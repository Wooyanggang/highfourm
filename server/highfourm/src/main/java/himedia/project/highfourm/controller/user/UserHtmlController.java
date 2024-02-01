package himedia.project.highfourm.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import himedia.project.highfourm.dto.user.UserDTO;
import himedia.project.highfourm.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserHtmlController {

	private final UserService service;
	
	@GetMapping("/users/new")
	public String addForm() {
	    return "userForm";
	}
	
	@PostMapping(value = "/users/new" )
	public String addNewUser(@RequestBody UserDTO userDTO, HttpSession session) {
//		Long adminCompanyId = (Long)session.getAttribute("companyId");
//		service.save(userDTO, adminCompanyId);
		log.info("실행!");
		log.info("통신 성공 값 : " + userDTO.getPosition());
		service.save(userDTO);
		log.info("session ? " + session.getAttribute("companyId"));
		
		return "redirect:http://localhost:3000/users";
	}
	
	@GetMapping("/users/edit/{empNo}")
	public String selectUser(@RequestParam("empNo") Long empNo, Model model) {
		log.info("empNo : ", empNo);
		UserDTO user = service.findByUserNo(empNo);
		model.addAttribute("user", user);
		return "userEditForm";
	}
}
