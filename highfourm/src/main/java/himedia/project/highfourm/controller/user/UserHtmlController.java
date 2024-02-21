package himedia.project.highfourm.controller.user;

import java.io.IOException;

import org.springframework.security.core.Authentication;
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
import himedia.project.highfourm.service.email.GmailService;
import himedia.project.highfourm.service.email.SmtpEmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 한혜림
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class UserHtmlController {

	private final UserService service;
	private final EmailService emailService;
//	private final GmailService gmailService;
	private final SmtpEmailService smtpEmailService;
	
	/**
	 * 사용자 등록 페이지
	 */
	@GetMapping("/users/new")
	public String addForm(Model model) {
		model.addAttribute("userAddDTO", new UserAddDTO());
	    return "userForm";
	}
	
	/**
	 * 사용자 등록
	 */
	@PostMapping("/users/new" )
	public String addNewUser(@ModelAttribute @Valid UserAddDTO userAddDTO, BindingResult bindingResult, 
			Authentication authentication, Model model) throws MessagingException, IOException {
		
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
		
//		gmailService.sendEmail(userAddDTO, authentication);
		smtpEmailService.createEmail(userAddDTO, authentication);	
		return "redirect:/users";
	}
	
	/**
	 * 사용자 수정 페이지
	 */
	@GetMapping("/users/edit/{empNo}")
	public String selectUser(@PathVariable("empNo") Long empNo, Authentication authentication, Model model) {
		UserEditDTO user = service.findByEmpNoforEdit(empNo, authentication);
		
		model.addAttribute("userEditDTO", user);
		return "userEditForm";
	}
	
	/**
	 * 사용자 수정
	 */
	@PutMapping("/users/edit/{userNo}")
	public String editUser(@PathVariable("userNo") Long userNo, @ModelAttribute @Valid UserEditDTO userEditDto, 
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "userEditForm";
		}
		service.updateUser(userEditDto);

		return "redirect:/users";
	}
	
	/**
	 * 사용자 인증 이메일 링크 페이지
	 */
	@GetMapping("/confirm-email")
	public String viewConfirmEmail(@RequestParam(value = "token") String token, @RequestParam(value = "empNo") Long empNo) {
		try {
			emailService.confirmEmail(token);
			return "redirect:/users/join/"+empNo.toString()+"?token="+token;
		} catch (Exception e) {
			return "tokenError";
		}
	}
	
}
