//package himedia.project.highfourm.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import himedia.project.highfourm.service.email.EmailService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RequiredArgsConstructor
//public class EmailController {
//
//	private final EmailService service;
//	
//	@GetMapping("/confirm-email")
//	public String viewConfirmEmail(@Valid @RequestParam String token) throws Exception {
//		service.confirmEmail(token);
//		return "redirect:/users/join";
//	}
//}
