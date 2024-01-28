package himedia.project.highfourm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@PostMapping("/users/new")
	public String userNew() {
//	public Map<String, Object> userNew(@RequestBody HashMap<String, Object> map) {
//		System.out.println(map);
//		Map<String, Object> result = map;
		return "POST 성공";
//		return result;
	}
	
	@GetMapping("/users")
	public String user() {
		return "GET 성공!!!";
	}
	
}
