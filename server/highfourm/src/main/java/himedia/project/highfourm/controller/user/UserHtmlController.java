package himedia.project.highfourm.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import himedia.project.highfourm.dto.user.UserAddDTO;
import himedia.project.highfourm.dto.user.UserDTO;
import himedia.project.highfourm.dto.user.UserEditDTO;
import himedia.project.highfourm.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserHtmlController {

	private final UserService service;
	
	@GetMapping("/users/new")
	public String addForm(Model model) {
		model.addAttribute("userAddDTO", new UserAddDTO());
	    return "userForm";
	}
	
	@PostMapping("/users/new" )
	public String addNewUser(@ModelAttribute @Valid UserAddDTO userAddDTO, BindingResult bindingResult, 
			HttpSession session, Model model) {
//		Long adminCompanyId = (Long)session.getAttribute("companyId");
//		service.save(userDTO, adminCompanyId);
//		log.info("session ? " + session.getAttribute("companyId"));
		
		if(bindingResult.hasErrors()) {
			return "userForm";
		}
		
		log.info("통신 성공 값 : " + userAddDTO.getPosition());
		
		service.save(userAddDTO);
		
		return "redirect:/users";
	}
	
	@GetMapping("/users/edit/{userNo}")
	public String selectUser(@PathVariable("userNo") Long userNo, Model model) {
		UserDTO user = service.findByUserNo(userNo);
		
		UserEditDTO editDTO = UserEditDTO.builder()
				.userNo(user.getUserNo())
				.userName(user.getUserName())
				.empNo(user.getEmpNo())
				.position(user.getPosition())
				.birth(user.getBirth())
				.email(user.getEmail())
				.build();
		
		model.addAttribute("userEditDTO", editDTO);
		return "userEditForm";
	}
	
	@PutMapping("/users/edit/{userNo}")
	public String editUser(@ModelAttribute @Valid UserEditDTO userEditDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "userEditForm";
		}

		return "redirect:/users";
	}
	
	@DeleteMapping("/users/delete/{deleteUserNo}")
	public String deleteByUserNo(@PathVariable(value = "deleteUserNo") String deleteUserNo) {
		Long userNo = Long.parseLong(deleteUserNo);
		service.delete(userNo);
		log.info("delete : " + deleteUserNo);
		
		return "redirect:/users";
	}
}
