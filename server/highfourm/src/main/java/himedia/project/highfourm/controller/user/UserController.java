package himedia.project.highfourm.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.user.UserDTO;
import himedia.project.highfourm.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
//@RequestMapping("/users")
public class UserController {
	
	private final UserService service;
	
	@GetMapping("/users")
	public List<UserDTO> selectUserList() {
		return service.findAllUsers();
	}
	
	@GetMapping("/users/search")
	public List<UserDTO> searchUserList(@RequestParam(value = "searchType") String searchType, @RequestParam(value = "search") String search) {
		List<UserDTO> result = null;
		
			if(searchType.equals("사원명")) {
				result = service.findByUserName(search);
			} else if(searchType.equals("사번")) {
				result = service.findByEmpNo(Long.parseLong(search));
			} else if(searchType.equals("이메일")) {
				result = service.findByEmail(search);
			}
			if (result.isEmpty()) {
				result = new ArrayList<UserDTO>();
			}
		
		return result;
	}
	


	@PutMapping("/users/edit/{empNo}")
	public String updateByUserNo() {
		return "redirect:/users/edit";
	}
	
	@DeleteMapping("/users/delete/{deleteUserNo}")
	public String deleteByUserNo(@PathVariable(value = "deleteUserNo") String deleteUserNo) {
		Long userNo = Long.parseLong(deleteUserNo);
		
		log.info("delete : " + deleteUserNo);
		
		return "redirect:http://localhost:3000/users";
	}
	
}
