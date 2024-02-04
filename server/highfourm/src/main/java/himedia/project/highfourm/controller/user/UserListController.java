package himedia.project.highfourm.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.user.UserDTO;
import himedia.project.highfourm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
//@RequestMapping("/users")
public class UserListController {
	
	private final UserService service;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> selectUserList() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setCacheControl(CacheControl.noStore());

	    List<UserDTO> users = service.findAllUsers();
	    
	    return new ResponseEntity<>(users, headers, HttpStatus.OK);
	}
	
	@GetMapping("/users/search")
	public ResponseEntity<List<UserDTO>> searchUserList(@RequestParam(value = "searchType") String searchType, @RequestParam(value = "search") String search) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setCacheControl(CacheControl.noStore());
	    
	    List<UserDTO> result = null;
		
			if(searchType.equals("사원명")) {
				result = service.findByUserName(search);
			} else if(searchType.equals("사번")) {
				result = service.findByEmpNo(Long.parseLong(search));
			} else if(searchType.equals("이메일")) {
				result = service.findByEmail(search);
			}
			if (result == null) {
				result = new ArrayList<UserDTO>();
			}
			
		return new ResponseEntity<>(result, headers, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/delete/{deleteUserNo}")
	public String deleteByUserNo(@PathVariable(value = "deleteUserNo") String deleteUserNo) {
		Long userNo = Long.parseLong(deleteUserNo);
		service.delete(userNo);
		
		return "redirect:/users";
	}
}
