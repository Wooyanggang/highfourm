package himedia.project.highfourm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@GetMapping("/users")
	public String user() {
		System.out.println("성공");
		return "GET 성공!!!";
	}

	@PostMapping("/users/new")
	public String userNew() {
//	public Map<String, Object> userNew(@RequestBody HashMap<String, Object> map) {
//		System.out.println(map);
//		Map<String, Object> result = map;
		return "POST 성공";
//		return result;
	}
	
	@PostMapping("/users/edit")
	public String userEdit() {
		return "redirect:/users/edit";
	}
	
}
