package himedia.project.highfourm.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import himedia.project.highfourm.dto.user.UserAddDTO;
import himedia.project.highfourm.dto.user.UserEditDTO;
import himedia.project.highfourm.service.UserService;
import himedia.project.highfourm.service.email.EmailService;
import himedia.project.highfourm.service.email.EmailTokenService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserHtmlController {

	private final UserService service;
	private final EmailTokenService emailTokenService;
	private final EmailService emailService;
	
	@GetMapping("/users/new")
	public String addForm(HttpSession session, Model model) {
		model.addAttribute("userAddDTO", new UserAddDTO());
		log.info("session ? " + session.getAttribute("companyId"));
	    return "userForm";
	}
	
	@PostMapping("/users/new" )
	public String addNewUser(@ModelAttribute @Valid UserAddDTO userAddDTO, BindingResult bindingResult, 
			HttpSession session, Model model) {
//		Long adminCompanyId = (Long)session.getAttribute("companyId");
//		service.save(userAddDTO, adminCompanyId);
		log.info("session ? " + session.getAttribute("companyId"));
		
		if(bindingResult.hasErrors()) {
			return "userForm";
		}
		
		if(!service.isEmailUnique(userAddDTO.getEmail())) {
			bindingResult.rejectValue("email", "error.user", "이미 사용중인 이메일입니다.");
			return "userForm";
		}
		
		if(!service.isEmpNoUnique(userAddDTO.getEmpNo())) {
			bindingResult.rejectValue("empNo", "error.user", "이미 등록된 사번입니다.");
			return "userForm";
		}
		
		String check = emailTokenService.createEmail(userAddDTO);
		return "redirect:/users";
	}
	
	@GetMapping("/users/edit/{userNo}")
	public String selectUser(@PathVariable("userNo") Long userNo, Model model) {
		UserEditDTO user = service.findByUserNoforEdit(userNo);
		
		model.addAttribute("userEditDTO", user);
		return "userEditForm";
	}
	
	@PutMapping("/users/edit/{userNo}")
	public String editUser(@PathVariable("userNo") Long userNo, @ModelAttribute @Valid UserEditDTO userEditDto, 
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "userEditForm";
		}
		service.updateUser(userEditDto);

		return "redirect:/users";
	}
	
	@GetMapping("/confirm-email")
	public String viewConfirmEmail(@RequestParam(value = "token") String token, @RequestParam(value = "userNo") Long userNo) throws Exception {
		emailService.confirmEmail(token);
		return "redirect:/users/join/userNo="+userNo.toString();
	}
	
}
