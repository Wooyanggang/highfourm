package himedia.project.highfourm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@PostMapping("/users/new")
	public String userNew() {
		return "POST 성공";
	}
	
}
