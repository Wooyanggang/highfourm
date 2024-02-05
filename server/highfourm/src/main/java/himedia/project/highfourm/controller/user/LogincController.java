package himedia.project.highfourm.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import himedia.project.highfourm.dto.user.UserJoinDTO;
import himedia.project.highfourm.service.JoinService;
import himedia.project.highfourm.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogincController {
	
	private final JoinService joinService;
	
	//로그인 페이지
	@GetMapping("/")
	public String login() {
		
		return "login";
	}
	//회원가입 페이지
	@GetMapping("/users/join")
	public String signUp() {
		
		return "join";
	}
	
	//회원가입 처리
	@PostMapping("/users/join")
	public String signUpProcess(UserJoinDTO joinDTO) {
		joinService.joinProcess(joinDTO);
		return "join";
	}
	
	//비회원 로그인 시
	@GetMapping("/notice")
	public String notice() {
		
		return "http://localhost:9000/notice";
	}
	
	@ResponseBody
    @GetMapping("/admin")
    public String adminP() {

        return "admin Controller";
    }
	
}




