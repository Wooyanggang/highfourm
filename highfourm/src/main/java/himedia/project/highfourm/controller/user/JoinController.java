package himedia.project.highfourm.controller.user;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import himedia.project.highfourm.dto.user.UserJoinFormDTO;
import himedia.project.highfourm.entity.EmailToken;
import himedia.project.highfourm.service.JoinService;
import himedia.project.highfourm.service.email.EmailService;
import himedia.project.highfourm.validation.CheckPasswordValidator;
import himedia.project.highfourm.validation.CheckUserIdValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class JoinController {
	
	private final JoinService joinService;
	private final CheckUserIdValidator checkUserIdValidator;
	private final CheckPasswordValidator checkPasswordValidator;
	private final EmailService emailService;
	
	@InitBinder
	public void validatorBinder(WebDataBinder binder) {
		binder.addValidators(checkUserIdValidator);
		binder.addValidators(checkPasswordValidator);
	}
	
	//회원가입 폼
    @GetMapping("/users/join/{empNo}")
    public String signUp(@PathVariable("empNo") Long empNo, Model model) throws Exception {
    	UserJoinFormDTO userJoinFormDTO = joinService.findByEmpNO(empNo);
    	model.addAttribute("userJoinFormDTO", userJoinFormDTO);
    	return "join";
    }
//    @GetMapping("/users/join/{empNo}/{token}")
//    public String signUp(@PathVariable("empNo") Long empNo, @PathVariable("token") String token, Model model) throws Exception {
//    	try {
//    		EmailToken emailToken = emailService.findByToken(token);
//    		if (token.equals(emailToken.getId())) {
//    			UserJoinFormDTO userJoinFormDTO = joinService.findByEmpNO(empNo);
//    			model.addAttribute("userJoinFormDTO", userJoinFormDTO);
//    			return "join";
//    		}else {
//    			return"tokenError";
//    		}
//    	} catch (Exception e) {
//    		return "tokenError";
//    	}
//
//    }
	
	//회원가입 처리
	@PostMapping("/users/join/{empNo}")
	public String signUpProcess(@PathVariable("empNo") Long empNo,
			@Valid UserJoinFormDTO joinDTO, BindingResult bindingResult,  Model model) {
		
		if(bindingResult.hasErrors()) {
			// 회원가입 실패 시 데이터 유지
			model.addAttribute("userJoinFormDTO", joinDTO);
			
            Map<String, String> validatorResult = joinService.validateHandling(bindingResult);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
			
			return "join";
		}
		
		joinService.joinProcess(joinDTO, empNo);
		return "redirect:/";
	}
	
}